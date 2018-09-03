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
        for i in range(0,n+1):
            #j: ending index of sum
            for j in range(i,n+1):
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
        mss_right = algorithms.junior(mid+1, right, in_list)
        
        # Find the MSS that intersects both the left and right halves
        mss_middle = algorithms.junior_middle(left, mid, right, in_list)
        # Timer end
        end = timer()
        elapsed_time = end - start
        algorithms.junior_time_holder = elapsed_time
        return max(mss_left, mss_middle, mss_right)
#------------------------------------------------------------------------------   
    # Junior Middle algorithm
    def junior_middle(left, mid, right, in_list = []):
        # Variables
        #max_left_sum = NEGATIVE_INFINITY
        max_left_sum = float('-inf')
        #max_right_sum = 0
        sum1 = 0
        # Begin algorithm
        for i in range(mid,left-1,-1):
            sum1 += in_list[i]
            
            if sum1 > max_left_sum:
                max_left_sum = sum1
                
        max_right_sum = float('-inf')
        sum1 = 0
        for i in range(mid+1,right+1):
            sum1 += in_list[i]
            
            if sum1 > max_right_sum:
                max_right_sum = sum1
        
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
        for i in range(0,n):
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
        input_section = int(input('Maximum Subsequence Sum Problem Program\n\nChoose which part of the project you want to do. \n1)Part 1\n2)Part 2\n3)Part 3\n0)Quit\n'))
        if input_section == 1:
        # Begin part one
            main_li.clear()
            input_string2 = input('Begin Part One.\nEnter a comma delimited array of integers.\n')
            string_li = input_string2.split(",")
            main_li = []
            for element in string_li:
                main_li.append(int(element))
            # Run all 4 algorithms to get the MSS
            print('Freshman MSS:',algorithms.freshman(main_li))
            print('Sophmore MSS:',algorithms.sophmore(main_li))
            print('Junior MSS:'  ,algorithms.junior(0, len(main_li)-1, main_li))
#            print('The junior MSS is:'  ,algorithms.junior(main_li))
            print('Senior MSS:'  ,algorithms.senior(main_li))
            input_bool = bool(0)
        if input_section == 2:
        # Begin Part Two
            # Random Array function prompts user for size of array
            main_li = algorithms.random_array()
            print('array ',main_li)
            input_stringA = int(input('Begin Part 2.\nEnter four digit string for the algorithms you want to run.\n'))
            # Takes user input and breaks it apart to decipher which algorithms (or all) to run
            p = str(input_stringA)
            input_stringA_li = list(p)
            s = []
            for e in input_stringA_li:
                a = int(e)
                s.append(a)
            for isa in range(0,len(s)):
                if s[isa] == 1:
                    print('Freshman MSS:',algorithms.freshman(main_li))
                elif s[isa] == 2:
                    print('Sophmore MSS:',algorithms.sophmore(main_li))
                elif s[isa] == 3:
                    print('Junior MSS:'  ,algorithms.junior(0, len(main_li)-1, main_li))
                elif s[isa] == 4:
                    print('Senior MSS:',algorithms.senior(main_li))
            # Print the running times of the algorithms
            for isac in range(0,len(s)):
                if s[isac] == 1:
                    print('Freshman algorithm took:',format(algorithms.freshman_time_holder * 1000, '.3f'),'ns to compute.')
                elif s[isac] == 2:
                    print('Sophmore algorithm took:',format(algorithms.sophmore_time_holder * 1000, '.3f'),'ns to compute.')
                elif s[isac] == 3:
                    print('Junior algorithm took:',format(algorithms.junior_time_holder * 1000, '.3f'), 'ns to compute.')
                elif s[isac] == 4:
                    print('Senior algorithm took:',format(algorithms.senior_time_holder * 1000, '.3f'), 'ns to compute.')
            input_bool = bool(0)            
        if input_section == 0:
            # Quit
            sys.exit(0)
        if input_section == 3:
        # Begin Part 3
            main_li.clear()    
            input_alg_choice = int(input('Begin Part 3.\nChoose an algorithm\n1)Freshman\n2)Sophomre\n3)Junior\n4)Senior\n0)Quit\n'))
            # Generate random numbers, the function prompts user for the amount        
            main_li = algorithms.random_array()
            # Print the MSS based on selection
            if input_alg_choice == 1:
                print('Freshman MSS:',algorithms.freshman(main_li))
                enter_m = int(input('Enter m:\n'))
                prediction = (algorithms.freshman_time_holder / len(main_li)**3) * enter_m**3
                print('\nPrediction the sophmore algorithm will take for',enter_m,'elements is',format(prediction * 1000, '.3f'), 'ns')
                print('Actual time required for',len(main_li),'elements',format(algorithms.freshman_time_holder * 1000, '.3f'), 'ns')
            elif input_alg_choice == 2:
                print('Sophmore MSS:',algorithms.sophmore(main_li))
                enter_m = int(input('Enter m:\n'))
                prediction = (algorithms.sophmore_time_holder / len(main_li)**2) * enter_m**2
                print('\nPrediction the sophmore algorithm will take for',enter_m,'elements is',format(prediction * 1000, '.3f'), 'ns')
                print('Actual time required for',len(main_li),'elements',format(algorithms.sophmore_time_holder * 1000, '.3f'), 'ns')
            elif input_alg_choice == 3:
                print('Junior MSS:'  ,algorithms.junior(0, len(main_li)-1, main_li))
                enter_m = int(input('Enter m:\n'))
                prediction = algorithms.junior_time_holder / (len(main_li)*(math.log10(len(main_li)))) * (enter_m*(math.log(enter_m)))
                print('\nPrediction the sophmore algorithm will take for',enter_m,'elements is',format(prediction * 1000, '.3f'), 'ns')
                print('Actual time required for',len(main_li),'elements',format(algorithms.junior_time_holder * 1000, '.3f'), 'ns')
            elif input_alg_choice == 4:
                print('Senior MSS:',algorithms.senior(main_li))
                enter_m = int(input('Enter m:\n'))
                prediction = (algorithms.senior_time_holder / len(main_li)) * enter_m
                print('\nPrediction the sophmore algorithm will take for',enter_m,'elements is',format(prediction * 1000, '.3f'), 'ns')
                print('Actual time required for',len(main_li),'elements',format(algorithms.senior_time_holder * 1000, '.3f'), 'ns')

    if input_choice == 0:
        input_bool == bool(0)        
     
print('End of program.')