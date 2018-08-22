# Kacy Rowe
# CECS 328 - Data Structures and Algorithms
# Project 1

# Imports
from timeit import default_timer as timer
import math
import sys
import random

class algorithms:
#------------------------------------------------------------------------------
    # Global variables
    freshman_time_holder = 0.0
    sophmore_time_holder = 0.0
    junior_time_holder = 0.0
    senior_time_holder = 0.0
    
    # Freshman algorithm
    def freshman(in_list = []):
        # Variables
        max_sum = 0
        # Get size of length
        n = len(in_list)
        # Timer start
        start = timer()
        #i: starting index of sum
        for i in range(0,n-1):
            #j: ending index of sum
            for j in range(i,n-1):
                this_sum = 0
                for k in range(i,j):
                    this_sum += int(in_list[k])
                if this_sum > max_sum:
                    max_sum = this_sum
        # end of for loops
        
        # Timer end
        end = timer()
        elapsed_time = end - start
        algorithms.freshman_time_holder = elapsed_time
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
        algorithms.sophmore_time_holder = elapsed_time
        return max_sum
#------------------------------------------------------------------------------
    # Junior algorithm
    def junior(left, right, in_list = []):
        # Assume: we are only interested in the MSS that is found between a[left] and a[right].
        # Initial call to mss_junior: mss_junior(a,0,n-1)
        # Timer start
        start = timer()
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
        # Timer end
        end = timer()
        elapsed_time = end - start
        algorithms.junior_time_holder = elapsed_time
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
        algorithms.senior_time_holder = elapsed_time
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
        # Begin main part of program
print('Welcome!\n')
main_li = []
input_bool = bool(1) 
input_bool2 = bool(1)
input_choice = 0
# Begin user interaction
while input_bool !=(0):
    while input_bool2 !=(0):
        input_choice = int(input('Choose an algorithm \n1)Freshman\n2)Sophmore\n3)Junior\n4)Senior\n0)Quit\n'))
        if input_choice == 1:
        # Freshman algorithm
            main_li.clear()
            input_choice = int(input('\n1)Random numbers\n2)Input your own numbers\n0)Quit\n'))
            if input_choice == 1:
                # Generate random numbers here
                main_li = algorithms.random_array()
                enter_n = len(main_li)
                # Run algorithm get T1 here
                print('The freshman MSS is:',algorithms.freshman(main_li))
                enter_m = int(input('Enter m:\n'))
                # Here is where the prediction and actual time get calculated
                prediction = (algorithms.freshman_time_holder / enter_n**3 * enter_m**3)
                print('\nI predict the freshman algorithm will take:',(format(prediction, '.6f')))
                print('Actual take required:',(format(algorithms.freshman_time_holder, '.6f')))
                input_bool = bool(0)
            if input_choice == 2:
                # User enters numbers
                input_string2 = input('Enter a comma delimited array of integers.\n')
                string_li = input_string2.split(",")
                main_li = []
                for element in string_li:
                    main_li.append(int(element))
                # Run algorithm get T1 here
                print('The freshman MSS is:',algorithms.freshman(main_li))
                enter_m = int(input('Enter m:\n'))
                # Here is where the prediction and actual time get calculated
                prediction = (algorithms.freshman_time_holder / enter_n**3) * enter_m**3
                print('\nI predict the freshman algorithm will take:',(format(prediction, '.6f')))
                print('Actual take required:',(format(algorithms.freshman_time_holder, '.6f')))
                input_bool = bool(0)
        if input_choice == 0:
            # Quit
            sys.exit(0)
        if input_choice == 2:
            # Sophmore algorithm
            main_li.clear()
            input_choice = int(input('\n1)Random numbers \n2)Input your own numbers\n0)Quit\n'))
            if input_choice == 1:
                # Generate random numbers here
                main_li = algorithms.random_array()
                enter_n = len(main_li)
                # Run algorithm get T1 here
                print('The sophmore MSS is:',algorithms.sophmore(main_li))
                enter_m = int(input('Enter m:\n'))
                # Here is where the prediction and actual time get calculated
                prediction = (algorithms.sophmore_time_holder / enter_n**2) * enter_m**2
                print('\nI predict the sophmore algorithm will take:',(format(prediction, '.6f')))
                print('Actual take required:',(format(algorithms.sophmore_time_holder, '.6f')))
                input_bool = bool(0)
        if input_choice == 2:
            # User enters numbers
            input_string2 = input('Enter a comma delimited array of integers.\n ')
            string_li = input_string2.split(",")
            main_li = []
            for element in string_li:
                main_li.append(int(element))
            # Run algorithm get T1 here
            print('The sophmore MSS is:',algorithms.sophmore(main_li))
            enter_m = int(input('Enter m:\n'))
            # Here is where the prediction and actual time get calculated
            prediction = (algorithms.sophmore_time_holder / enter_n**2) * enter_m**2
            print('\nI predict the sophmore algorithm will take:',(format(prediction, '.6f')))
            print('Actual take required:',(format(algorithms.sophmore_time_holder, '.6f')))
            input_bool = bool(0)
        if input_choice == 0:
            # Quit
            sys.exit(0)
        if input_choice == 3:
            # Junior algorithm
            main_li.clear()
            input_choice = int(input('\n1)Random numbers\n2)Input your own numbers\n0)Quit\n'))
            if input_choice == 1:
                # Generate random numbers here
                main_li = algorithms.random_array()
                enter_n = len(main_li)
                # Run algorithm get T1 here
                print('The junior MSS is:',algorithms.junior(0, len(main_li)-1, main_li))
                enter_m = int(input('Enter m:\n'))
                # Here is where the prediction and actual time get calculated
                prediction = algorithms.junior_time_holder / (enter_n*(math.log10(enter_n))) * (enter_m*(math.log(enter_m)))
                print('\nI predict the junior algorithm will take:',(format(prediction, '.6f')))
                print('Actual take required:',(format(algorithms.junior_time_holder, '.6f')))
                input_bool = bool(0)
            if input_choice == 2:
                # User enters numbers
                input_string2 = input('Enter a comma delimited array of integers.\n ')
                string_li = input_string2.split(",")
                main_li = []
                for element in string_li:
                    main_li.append(int(element))
                # Run algorithm get T1 here
                print('The junior MSS is:',algorithms.junior(main_li))
                enter_m = int(input('Enter m:\n'))
                # Here is where the prediction and actual time get calculated
                prediction = algorithms.junior_time_holder / (enter_n*(math.log10(enter_n))) * (enter_m*(math.log(enter_m)))
                print('\nI predict the junior algorithm will take:',(format(prediction, '.6f')))
                print('Actual time required:',(format(algorithms.junior_time_holder, '.6f')))
                input_bool = bool(0)
            if input_choice == 0:
                # Quit
                sys.exit(0)
        if input_choice == 4:
            # Senior algorithm
            main_li.clear()
            input_choice = int(input('\n1)Random numbers\n2)Input your own numbers\n0)Quit\n'))
            if input_choice == 1:
                # Generate random numbers here
                main_li = algorithms.random_array()
                enter_n = len(main_li)
                # Run algorithm get T1 here
                print('The senior MSS is:',algorithms.senior(main_li))
                enter_m = int(input('Enter m:\n'))
                # Here is where the prediction and actual time get calculated
                prediction = (algorithms.senior_time_holder / enter_n) * enter_m
                print('\nI predict the senior algorithm will take:',(format(prediction, '.6f')))
                print('Actual take required:',(format(algorithms.senior_time_holder, '.6f')))
                input_bool = bool(0)
            if input_choice == 2:
                # User enters numbers
                input_string2 = input('Enter a comma delimited array of integers.\n ')
                string_li = input_string2.split(",")
                main_li = []
                for element in string_li:
                    main_li.append(int(element))
                # Run algorithm get T1 here
                print('The senior MSS is:',algorithms.senior(main_li))
                enter_m = int(input('Enter m:\n'))
                # Here is where the prediction and actual time get calculated
                prediction = (algorithms.senior_time_holder / enter_n) * enter_m
                print('\nI predict the senior algorithm will take:',(format(prediction, '.6f')))
                print('Actual time required:',(format(algorithms.senior_time_holder, '.6f')))
                input_bool = bool(0)
            if input_choice == 0:
                # Quit
                sys.exit(0)
    if input_choice == 0:
        input_bool == bool(0)        
     
print('End of program.')