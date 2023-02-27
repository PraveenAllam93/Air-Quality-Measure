import json
import boto3
import urllib.parse
import ast

# Import the hazardous_check function from the air_quality_check module.
from hazardous_check import hazardous_check

s3_client = boto3.client('s3')
# Create a DynamoDB resource object.
dynamodb = boto3.resource('dynamodb')

# Specify the name of the DynamoDB table to use.
table_name = dynamodb.Table('aqms_final_data')

# Define a function to check if any of the fields in a list of data could contain errors.
def error_check_in_data(error_possible_data):
    error_check = [" ", "-", "#", "/"]
    return any(x in error_check for x in error_possible_data)

# Define the Lambda function handler, which takes two parameters:
#   event: The event data passed to the Lambda function.
#   context: The runtime information for the Lambda function.
def lambda_handler(event, context):
  
  
    for record in event['Records']:
        # Get the object key from the event record
        object_key = urllib.parse.unquote_plus(record['s3']['object']['key'])
        print(object_key)
        # Delete the object from S3
        
    
    bucket = event['Records'][0]['s3']['bucket']['name']
    
    json_file_name = event['Records'][0]['s3']['object']['key']
    
    json_object = s3_client.get_object(Bucket=bucket,Key=json_file_name)
    file_reader = json_object['Body'].read().decode("utf-8")
    file_reader = ast.literal_eval(file_reader)
    event = file_reader
    print(event)
    
    # Query the DynamoDB table to get the number of existing items.
    response = table_name.scan()
    
    
    # Check for errors in the input data.
    error_possible_data = [event[field] for field in ['o2_level', 'co2_level', 'so2_level', 'co_level', 'c_level']]
    isError = error_check_in_data(error_possible_data)
    if (isError == True):
        # If errors are detected, set the air quality index to '#' and add an error message to the event data.
        air_quality_index = '#'
        event['aqms_message'] = "There is some error with the device."
    else:
        # If no errors are detected, calculate the air quality index using the hazardous_check function.
        air_quality_index = sum([int(event[field]) for field in ['co2_level', 'so2_level', 'co_level', 'c_level']]) - int(event['o2_level'])
        event['aqms_message'] = hazardous_check(air_quality_index)
    
    # Add the air quality index, error status, and ID to the event data.
    event['air_quality_index'] = str(air_quality_index)
    event['isError'] = str(isError)
    
    
    # Save the event data to the DynamoDB table.
    try:
        response = table_name.put_item(Item=event)
        s3_client.delete_object(Bucket='aqms-data-intermediate', Key=object_key)
        return {
            'body': json.dumps('Data saved to Dynamo db successfully')
        }
    except Exception as e:
        print(e)
        raise e
