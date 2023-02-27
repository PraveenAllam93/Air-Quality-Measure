def hazardous_check(air_quality_index):
    # Check if the air quality index is less than or equal to 50.
    if air_quality_index <= 50:
        return "Air Quality is good and healthy :-)"
    # Check if the air quality index is between 51 and 100 (inclusive).
    elif (air_quality_index >= 51 and air_quality_index <= 100 ):
        return "Air Quality Moderate!!!!"
    # Check if the air quality index is between 101 and 150 (inclusive).
    elif (air_quality_index >= 101 and air_quality_index <= 150):
        return "Air Quality is which is unhealthy for sensetive people, please do vacate the floor if you're sensetive :-)"
    # Check if the air quality index is between 151 and 200 (inclusive).
    elif (air_quality_index >= 151 and air_quality_index <= 200):
        return "Air Quality is unhealthy, please do vacate the floor!!!!"
    # Check if the air quality index is between 201 and 300 (inclusive).
    elif (air_quality_index >= 201 and air_quality_index <= 300):
        return "Air Quality is extremely unhealthy, please do vacate the floor immediately!!!!!"
    # If none of the above conditions are met, assume the air quality index is above 300 (hazardous).
    return "Air Quality is hazardous, please do vacate the floor immediately, DANGER DANGER!!!!!"
