To run the Dropbox Diet:

In cmd type:

javac DropboxDiet.java

java DropboxDiet data_small.txt

java DropboxDiet data_large.txt


Description:

The Dropbox Diet

Help us keep our caloric intake in check. You'll be given a list of activities and their caloric impact. Write a program that outputs the names of activities a Dropboxer should choose to partake in so that the sum of their caloric impact is zero. Once an activity is selected, it cannot be chosen again.

Input
Your program reads an integer N (1 <= N <= 50) from STDIN representing the number of list items in the test input. The list is comprised of activities or food items and its respective calorie impact separated by a space, one pair per line. Activity names will use only lowercase ASCII letters and the dash (-) character.

Output
Output should be sent to stdout, one activity name per line, alphabetized. If there is no possible solution, the output should be no solution. If there are multiple solutions, your program can output any one of them. 


Developed by Daniel Gur using java version "1.6.0_29"