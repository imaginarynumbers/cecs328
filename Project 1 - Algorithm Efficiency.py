# Kacy Rowe
# CECS 328 - Data Structures and Algorithms
# Project 1

# Imports
from timeit import default_timer as timer
import random
# Global Items

class algorithms:
#------------------------------------------------------------------------------
    prediction_time_holder = 0.0
    last_function_used = " "
    # Freshman algorithm
    def freshman(in_list = []):
        # Variables
        max_sum = 0
        this_sum = 0
        # Get size of length
        n = len(in_list)
        print('n =',n)
        # Copy the list that was passed in
        li = list(in_list)
        # Timer start
        start = timer()
        #i: starting index of sum
        for i in range(0,n):
            
            #j: ending index of sum
            for j in range(i,n):
                this_sum = 0
                for k in range(i,j):
                    element = int(li[k])
                    this_sum += element
                    if this_sum > max_sum:
                        max_sum = this_sum
        # end of for loops
        
        # Timer end
        end = timer()
        elapsed_time = end - start
        algorithms.prediction_time_holder = elapsed_time
        print('(Freshman) Time elapsed: ', elapsed_time)
        algorithms.last_function_used = "freshman"
        return max_sum
#------------------------------------------------------------------------------      
    # Sophmore algorithm
    def sophmore(in_list = []):
        # Variables
        max_sum = 0
        this_sum = 0
        n = len(in_list)
        
        # Timer start
        start = timer()
        # i: Starting index of sum
        for i in range(0,n):
            this_sum = 0
            
            # Compute all sums that begin with index i
            for j in range(i,n):
                element = int(in_list[j])
                this_sum += element
                if this_sum > max_sum:
                    max_sum = this_sum
                    
        # end of for loops
        # Timer end
        end = timer()
        elapsed_time = end - start
        algorithms.prediction_time_holder = elapsed_time
        print('(Sophmore) Time elapsed: ', elapsed_time)
        algorithms.last_function_used = "sophmore"
        return max_sum
#------------------------------------------------------------------------------
    # Junior algorithm
    def junior(left, right, in_list = []):
        # Assume: we are only interested in the MSS that is found between a[left] and a[right].
        # Initial call to mss_junior: mss_junior(a,0,n-1)
        
        # Base case 1
        if right == left:
            return in_list[left]
        # Base case 2
        if right == left + 1:
            return max(in_list[left], in_list[right], in_list[left] + in_list[right])
        # Floor division to get the mid value
        mid = (left + right) // 2
        
        # Find the MSS that occurs in the left half of a
        mss_left = algorithms.junior(left, mid, in_list)
        
        # Find the MSS that occurs in the left half of a
        mss_right = algorithms.junior(left, mid, in_list)
        
        # Find the MSS that intersects both the left and right halves
        mss_middle = algorithms.junior_middle(left, mid, right, in_list)

        return max(mss_left, mss_right, mss_middle)
#------------------------------------------------------------------------------   
    # Junior Middle algorithm
    def junior_middle(left, mid, right, in_list = []):
        # Variables
        #max_left_sum = NEGATIVE_INFINITY
        max_left_sum = 0
        max_right_sum = 0
        sum1 = 0
        i = 0
        # Begin algorithm
        for i in range(left,mid):
            sum1 += int(in_list[i])
            
            if sum1 > max_left_sum:
                max_left_sum = sum1
        
        return max_left_sum + max_right_sum
#------------------------------------------------------------------------------     
    # Senior algorithm
    def senior(in_list = []):
        # Variables
        max_sum = 0
        this_sum = 0
        n = len(in_list)
        # Timer start
        start = timer()
        # Begin algorithm
        for i in range(0,n-1):
            element = in_list[i]
            this_sum += element
            
            if this_sum > max_sum:
                max_sum = this_sum
            elif this_sum < 0:
                this_sum = 0
    
        # Timer end
        end = timer()
        elapsed_time = end - start
        algorithms.prediction_time_holder = elapsed_time
        print('(Senior) Time elapsed: ', elapsed_time)
        algorithms.last_function_used = "senior"
        return max_sum
#------------------------------------------------------------------------------    
    # Generate a random array of numbers
    def random_array():
        # List to hold the random numbers
        random_li = []
        
        #Ask the user how many numbers they want to generate
        input_number = int(input('How many numbers would you like to generate?\n'))
        # Take a user defined number to use as limit
        for i in range (0,input_number):
            # Generate random numbers between -50 to 50
            random_li.append(random.randint(-50,50))
        return random_li     
#------------------------------------------------------------------------------
    # Generate a random array of numbers
    def prediction():        
        #Ask the user for n and m
        input_n = int(input('Please enter n: \n'))
        input_m = int(input('Please enter m: \n'))
        prediction = (algorithms.prediction_time_holder / input_m**3) * input_n
        return prediction
#------------------------------------------------------------------------------
        # Begin main part of program
print('Welcome!\n')
main_li = []
## Take user input
#input_string2 = input('Enter a comma delimited array of integers. ')
#string_li = input_string2.split(",")
#main_li = []
#for element in string_li:
#    main_li.append(int(element))
#print(main_li)
#length = int(len(main_li)-1)
#for i in range(0,len(main_li)-1):
#    if main_li[i] < 0:
#        print('not negative')
    
## Boolean variables for while loops
input_bool = bool(1) 
input_bool2 = bool(1)
input_choice = 0
# Switcher to allow user to choose which algorithm to use
#while input_bool != bool(0):
#    main_li.clear()
#    input_choice = int(input('\nPlease make a choice:\n1)Generate random numbers \n2)Input your own numbers'))
#    if input_choice == 1:
#        main_li = algorithms.random_array(main_li)
#        input_bool(1)
#    if input_choice == 2:
#        input_string2 = input('Enter a comma delimited array of integers. ')
#        string_li = input_string2.split(",")
#        main_li = []
#        for element in string_li:
#            main_li.append(int(element))
#        input_bool(1)
# Begin user interaction
while input_bool2 != bool(0):
    while input_bool != bool(0):
        main_li.clear()
        input_choice = int(input('\nPlease make a choice:\n1) Generate random numbers \n2) Input your own numbers\n3) Make prediction based on last algorithm used \n0) Quit program\n'))
        if input_choice == 1:
            main_li = algorithms.random_array()
            input_bool = bool(0)
        if input_choice == 2:
            input_string2 = input('Enter a comma delimited array of integers.\n ')
            string_li = input_string2.split(",")
            main_li = []
            for element in string_li:
                main_li.append(int(element))
            input_bool = bool(0)
        if input_choice == 3:
            if algorithms.prediction_time_holder == 0:
                print('Run an algorithm first.')
            else:
                print('I predict that',algorithms.last_function_used,'will take:',algorithms.prediction())
        elif input_choice == 0:
            break
    if input_choice == 0:
        break
    input_bool = bool(1)
    input_choice2 = int(input('\nPlease choose an algorithm \n1) Freshman \n2) Sophmore\n3) Junior\n4) Senior \n0) Quit program\n'))
    if input_choice2 == 1:
        # Call freshman function
        print('The freshman MSS is:',algorithms.freshman(main_li))
    elif input_choice2 == 2:
        # Call sophmore function
        print('The sophmore MSS is:',algorithms.sophmore(main_li))
    elif input_choice2 == 3:
        # Call junior function
        # Junior timer is here, due to recursion in function
        start = timer()
        print('The junior MSS is:',algorithms.junior(0, len(main_li)-1, main_li))
        end = timer()
        elapsed_time = end - start
        algorithms.prediction_time_holder = elapsed_time
        last_function_used = "junior"
        print('(Junior) Time elapsed: ', elapsed_time)
    elif input_choice2 == 4:
        # Call senior function
        print('The senior MSS is:',algorithms.senior(main_li))
    elif input_choice2 == 0:
        input_bool2 = bool(0)
        break
    else:
        # User gave bad input
        print('\nInvalid input')
# End of program prompt     
print('End of program.')
#    # Part II of Project
#input_string = int(input('What would you like to do? \n1: Enter your own numbers.\n2: Generate random numbers.'))        
    
# Switcher to call the user chosen function
#if input_string == 1: 
#    algorithms.random_array()