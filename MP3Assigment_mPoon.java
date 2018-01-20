/*--------------------------------------------------------------------------------------*/
/*  MP3Assigment_mPoon.java  -  This program allows a user to maintain their MP3
    collection and add new user databases. Options include: viewing collection, adding
    to collection, search music, sort music, create backup of collection, save as, delete
    songs from collection and even play a quick tune!                                   */
/*                                                                                      */
/*--------------------------------------------------------------------------------------*/
/*  Author: Michael Poon                                                                */
/*  Date: March 2015                                                                    */
/*--------------------------------------------------------------------------------------*/
/*  Input: Open user file, make selections                                              */
/*  Output: Username, Song, Artist's Name, Category, Play Music, etc.                   */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class MP3Assigment_mPoon
{
    static void line ()  //line method to create vertical line
    {
	System.out.println ("|--------------------------------------------------------|");
    }


    static void space ()  //space method to move to next line
    {
	System.out.println ();
    }


    static void tab (String[] name, int i)  //tab method to align song/artist/category/year display
    {
	if (name [i].length () >= 16) //adjust tabbing so longer song details align up with shorter song details, aesthetic appeal
	    System.out.print ("\t");
	else if (name [i].length () >= 8)
	    System.out.print ("\t\t");
	else
	    System.out.print ("\t\t\t");
    }


    static void user (String name, String[] songName, String[] artistName, String[] category, String[] year, int[] count, int MAX) throws IOException
    {
	//validate user, reads files to array
	BufferedReader reader = new BufferedReader (new FileReader ("Username.txt"));
	String line = null;
	String[] username = new String [MAX];
	int counter = 0, counter2 = 0;

	while ((line = reader.readLine ()) != null) //reads username file to see if username exists
	{
	    username [counter] = line;
	    counter++;
	}
	reader.close ();
	for (int i = 0 ; i < counter ; i++) //checks entered username in username database
	{
	    if (name.equals (username [i]))
	    {
		System.out.print ("Welcome " + name + "!");
		space ();

		BufferedReader reader2 = new BufferedReader (new FileReader (name + ".txt"));

		while ((line = reader2.readLine ()) != null) //reads username file info into four arrays: song name/artist/category/year
		{
		    songName [count [0]] = line;
		    artistName [count [0]] = reader2.readLine ();
		    category [count [0]] = reader2.readLine ();
		    year [count [0]] = reader2.readLine ();
		    count [0]++;
		}
		counter2++; //counter to confirm user is found in username database
		reader2.close ();
	    }
	}

	if (counter2 == 0) //if username not found in username database, create new user
	{
	    space ();
	    System.out.println ("Username Not Found.");
	    space ();

	    BufferedWriter writer = new BufferedWriter (new FileWriter ("Username.txt")); //add new username to username database
	    for (int i = 0 ; i < counter ; i++)
	    {
		writer.write (username [i]);
		writer.newLine ();
	    }
	    writer.write (name);
	    writer.close ();
	    BufferedWriter writer2 = new BufferedWriter (new FileWriter (name + ".txt")); //create new username file
	    writer2.close ();
	    System.out.println ("New Username Created: " + name + "\nBe sure to select ~Save Any Changes(Overwrite)~ when complete.");
	    space ();
	    System.out.print ("Welcome " + name + "!");
	    space ();
	}
    }


    static void display (String[] songName, String[] artistName, String[] category, String[] year, int[] count)
    {
	//display info for song name/artist/category/year
	System.out.println ("Song Name\t\tArtist Name\t\tCategory\t\tYear\t\t\t");
	space ();
	if (songName [0] == null) //if user has no songs saved, skip display
	{
	}
	else
	{
	    for (int i = 0 ; i < count [0] ; i++) //display songs saved to user file
	    {
		System.out.print (songName [i]);
		tab (songName, i); //tab method allows for proper alignment in display
		System.out.print (artistName [i]);
		tab (artistName, i);
		System.out.print (category [i]);
		tab (category, i);
		System.out.println (year [i]);
	    }
	}
    }


    static void add (String[] songName, String[] artistName, String[] category, String[] year, int[] count) throws IOException
    {
	//allows user to add song details to expand song database
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	System.out.print ("Song Name: "); //user enters in song details to each array one by one
	songName [count [0]] = stdin.readLine ();
	System.out.print ("Artist Name: ");
	artistName [count [0]] = stdin.readLine ();
	System.out.print ("Category: ");
	category [count [0]] = stdin.readLine ();
	System.out.print ("Year: ");
	year [count [0]] = stdin.readLine ();
	count [0]++;

    }


    static void search (String[] songName, String[] artistName, String[] category, String[] year, int[] count) throws IOException
    {
	//allows user to search for a particular song
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	String search;
	int selection, counter = 0;

	do
	{
	    System.out.println ("(1)Search By Song Name\n(2)Search By Artist Name\n(3)Search By Category\n(4)Search By Year\n(0)Return To Menu");
	    selection = Integer.parseInt (stdin.readLine ());


	    switch (selection)
	    {
		case 0:
		    break;
		case 1: //search by Song Name
		    space ();
		    System.out.print ("Song Name: ");
		    search = stdin.readLine ();
		    space ();
		    System.out.println ("Song Name\t\tArtist Name\t\tCategory\t\tYear\t\t\t");
		    space ();
		    for (int i = 0 ; i < count [0] ; i++) //searches through song list to find matches
		    {
			if (search.equals (songName [i]))
			{
			    System.out.print (songName [i]); //display songs that match search criteria
			    tab (songName, i);
			    System.out.print (artistName [i]);
			    tab (artistName, i);
			    System.out.print (category [i]);
			    tab (category, i);
			    System.out.println (year [i]);
			    counter++;
			}
		    }
		    space ();
		    if (counter == 0)

			System.out.println ("Not Found.");
		    break;
		case 2: //search by Artist
		    space ();
		    System.out.print ("Artist Name: ");
		    search = stdin.readLine ();
		    space ();
		    System.out.println ("Song Name\t\tArtist Name\t\tCategory\t\tYear\t\t\t");
		    space ();
		    for (int i = 0 ; i < count [0] ; i++) //searches through song list to find matches
		    {
			if (search.equals (artistName [i]))
			{
			    System.out.print (songName [i]); //display songs that match search criteria
			    tab (songName, i);
			    System.out.print (artistName [i]);
			    tab (artistName, i);
			    System.out.print (category [i]);
			    tab (category, i);
			    System.out.println (year [i]);
			    counter++;
			}
		    }
		    space ();
		    if (counter == 0)
			System.out.println ("Not Found.");
		    break;
		case 3: //search by Category
		    space ();
		    System.out.print ("Category: ");
		    search = stdin.readLine ();
		    space ();
		    System.out.println ("Song Name\t\tArtist Name\t\tCategory\t\tYear\t\t\t");
		    space ();
		    for (int i = 0 ; i < count [0] ; i++) //searches through song list to find matches
		    {
			if (search.equals (category [i]))
			{
			    System.out.print (songName [i]); //display songs that match search criteria
			    tab (songName, i);
			    System.out.print (artistName [i]);
			    tab (artistName, i);
			    System.out.print (category [i]);
			    tab (category, i);
			    System.out.println (year [i]);
			    counter++;
			}
		    }
		    space ();
		    if (counter == 0)
			System.out.println ("Not Found.");
		    break;
		case 4: //search by Year
		    space ();
		    System.out.print ("Year: ");
		    search = stdin.readLine ();
		    space ();
		    System.out.println ("Song Name\t\tArtist Name\t\tCategory\t\tYear\t\t\t");
		    space ();
		    for (int i = 0 ; i < count [0] ; i++) //searches through song list to find matches
		    {
			if (search.equals (year [i]))
			{
			    System.out.print (songName [i]); //display songs that match search criteria
			    tab (songName, i);
			    System.out.print (artistName [i]);
			    tab (artistName, i);
			    System.out.print (category [i]);
			    tab (category, i);
			    System.out.println (year [i]);
			    counter++;
			}
		    }
		    space ();
		    if (counter == 0)

			System.out.println ("Not Found.");
		    break;
	    }
	}
	while (selection != 0);
    }


    static void sort (String[] songName, String[] artistName, String[] category, String[] year, int[] count) throws IOException
    {
	//sort saved songs by song name/artist/category/year in alphabetical/ascending order
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	String temp;
	int selection, counter = 0;
	boolean swapped = false;
	do
	{
	    System.out.println ("(1)Sort By Song Name\n(2)Sort By Artist Name\n(3)Sort By Category\n(4)Sort By Year\n(0)Return To Menu");
	    selection = Integer.parseInt (stdin.readLine ());

	    switch (selection)
	    {
		case 0:
		    break;
		case 1: //sort by song name
		    for (int i = 0 ; i < count [0] - 1 ; i++) //modified bubble sort used
		    {
			swapped = false;
			for (int j = 0 ; j < ((count [0] - 1) - i) ; j++)
			    if (songName [j].compareTo (songName [j + 1]) > 0)
			    {
				swapped = true;
				temp = songName [j]; //switch song details
				songName [j] = songName [j + 1];
				songName [j + 1] = temp;
				temp = artistName [j];
				artistName [j] = artistName [j + 1];
				artistName [j + 1] = temp;
				temp = category [j];
				category [j] = category [j + 1];
				category [j + 1] = temp;
				temp = year [j];
				year [j] = year [j + 1];
				year [j + 1] = temp;
			    }
			if (!swapped)
			    i = count [0] - 1;
		    }
		    display (songName, artistName, category, year, count);
		    break;
		case 2: //sort by artist
		    for (int i = 0 ; i < count [0] - 1 ; i++) //modified bubble sort used
		    {
			swapped = false;
			for (int j = 0 ; j < ((count [0] - 1) - i) ; j++)
			    if (artistName [j].compareTo (artistName [j + 1]) > 0)
			    {
				swapped = true;
				temp = songName [j]; //switch song details
				songName [j] = songName [j + 1];
				songName [j + 1] = temp;
				temp = artistName [j];
				artistName [j] = artistName [j + 1];
				artistName [j + 1] = temp;
				temp = category [j];
				category [j] = category [j + 1];
				category [j + 1] = temp;
				temp = year [j];
				year [j] = year [j + 1];
				year [j + 1] = temp;
			    }
			if (!swapped)
			    i = count [0] - 1;
		    }
		    display (songName, artistName, category, year, count);
		    break;
		case 3: //sort by category
		    for (int i = 0 ; i < count [0] - 1 ; i++) //modified bubble sort used
		    {
			swapped = false;
			for (int j = 0 ; j < ((count [0] - 1) - i) ; j++)
			    if (category [j].compareTo (category [j + 1]) > 0)
			    {
				swapped = true;
				temp = songName [j]; //switch song details
				songName [j] = songName [j + 1];
				songName [j + 1] = temp;
				temp = artistName [j];
				artistName [j] = artistName [j + 1];
				artistName [j + 1] = temp;
				temp = category [j];
				category [j] = category [j + 1];
				category [j + 1] = temp;
				temp = year [j];
				year [j] = year [j + 1];
				year [j + 1] = temp;
			    }
			if (!swapped)
			    i = count [0] - 1;
		    }
		    display (songName, artistName, category, year, count);
		    break;
		case 4: //sort by year
		    for (int i = 0 ; i < count [0] - 1 ; i++) //modified bubble sort used
		    {
			swapped = false;
			for (int j = 0 ; j < ((count [0] - 1) - i) ; j++)
			    if (year [j].compareTo (year [j + 1]) > 0)
			    {
				swapped = true;
				temp = songName [j]; //switch song details
				songName [j] = songName [j + 1];
				songName [j + 1] = temp;
				temp = artistName [j];
				artistName [j] = artistName [j + 1];
				artistName [j + 1] = temp;
				temp = category [j];
				category [j] = category [j + 1];
				category [j + 1] = temp;
				temp = year [j];
				year [j] = year [j + 1];
				year [j + 1] = temp;
			    }
			if (!swapped)
			    i = count [0] - 1;
		    }
		    display (songName, artistName, category, year, count);
		    break;
	    }
	}
	while (selection != 0); //allows user to return to main menu

    }


    static void delete (String[] songName, String[] artistName, String[] category, String[] year, int[] count) throws IOException
    {
	//allows user to delete songs from their MP3 collection
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));

	String delete;
	int counter = 0, counter2 = 0;
	space ();
	System.out.print ("Song Name To Delete: "); //ask for song to delete
	delete = stdin.readLine ();
	space ();
	for (int i = 0 ; i < count [0] ; i++) //search for song through MP3 collection
	{
	    if (delete.equals (songName [i])) //display song deleted
	    {
		System.out.print (songName [i]);
		tab (songName, i);
		System.out.print (artistName [i]);
		tab (artistName, i);
		System.out.print (category [i]);
		tab (category, i);
		System.out.println (year [i]);
		counter2 = i; //save song position in each array
		counter++;
	    }

	}
	if (counter == 0)
	{
	    System.out.print ("Not Found."); //song name not found, return to main menu
	    space ();
	}
	else
	{
	    songName [counter2] = null; //set the value of each array to nothing in the position the deleted song was in
	    artistName [counter2] = null;
	    category [counter2] = null;
	    year [counter2] = null;
	    for (int i = counter2 ; i < count [0] - 1 ; i++) //empty position on array is now the value of the position above it
	    {
		songName [i] = songName [i + 1];
		artistName [i] = artistName [i + 1];
		category [i] = category [i + 1];
		year [i] = year [i + 1];

	    }
	    count [0] = count [0] - 1; //overall number of songs is decreased by 1
	    space ();
	    System.out.print (delete + " was successfully deleted.");
	    space ();
	}

    }


    static void backup (String[] songName, String[] artistName, String[] category, String[] year, int[] count, String name, int MAX) throws IOException
    {
	//create backup file (user_backup.txt)
	BufferedReader reader = new BufferedReader (new FileReader ("Username.txt"));
	String line = null;
	String[] username = new String [MAX];
	int counter = 0;

	while ((line = reader.readLine ()) != null) //read username file
	{
	    username [counter] = line;
	    counter++;
	}
	reader.close ();

	BufferedWriter writer = new BufferedWriter (new FileWriter ("Username.txt")); //add username to username file
	for (int i = 0 ; i < counter ; i++)
	{
	    writer.write (username [i]);
	    writer.newLine ();
	}
	writer.write (name);
	writer.close ();
	if (songName [0] == null) //if there are no songs in MP3 collection, skip writing songs to file
	{
	}
	else
	{
	    BufferedWriter writer2 = new BufferedWriter (new FileWriter (name + "_backup.txt")); //write songs to backup file
	    for (int i = 0 ; i < count [0] ; i++) //for loop used to save songs in order of array
	    {
		writer2.write (songName [i]);
		writer2.newLine ();
		writer2.write (artistName [i]);
		writer2.newLine ();
		writer2.write (category [i]);
		writer2.newLine ();
		writer2.write (year [i]);
		writer2.newLine ();
	    }
	}
	System.out.print ("File saved as: " + name + "_backup.txt");
	writer.close ();
	space ();
    }


    static void save (String[] songName, String[] artistName, String[] category, String[] year, int[] count, String name) throws IOException
    {
	//save current MP3 collection under username's file
	BufferedWriter writer = new BufferedWriter (new FileWriter (name + ".txt"));
	if (songName [0] == null) //if no songs are in MP3 collection, skip writing song details to file
	{
	}
	else
	{
	    for (int i = 0 ; i < count [0] ; i++) //write songs to file one by one
	    {
		writer.write (songName [i]);
		writer.newLine ();
		writer.write (artistName [i]);
		writer.newLine ();
		writer.write (category [i]);
		writer.newLine ();
		writer.write (year [i]);
		writer.newLine ();
	    }
	}
	System.out.print ("File saved as: " + name + ".txt");
	writer.close ();
	space ();
    }


    static void newUser (String[] songName, String[] artistName, String[] category, String[] year, int[] count, int MAX) throws IOException
    {
    //allows new user to be created or switch to existing user
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	int response, j = 0;
	String name;
	do
	{
	    System.out.println ("Select:\n(1)Create New User Database\n(2)Switch To Existing User\n(0)Return To Main Menu");
	    response = Integer.parseInt (stdin.readLine ());
	    if (response == 1)
	    {
		System.out.print ("Username: ");
		name = stdin.readLine ();

		BufferedReader reader = new BufferedReader (new FileReader ("Username.txt")); //read username file
		String line = null;
		String[] username = new String [MAX];
		int counter = 0, counter2 = 0;

		while ((line = reader.readLine ()) != null) //open username file to determine current usernames
		{
		    username [counter] = line;
		    counter++;
		}
		reader.close ();

		BufferedWriter writer = new BufferedWriter (new FileWriter ("Username.txt")); //write new username to username file
		for (int i = 0 ; i < counter ; i++)
		{
		    writer.write (username [i]);
		    writer.newLine ();
		}
		writer.write (name); //add new username at the bottom of the username list
		writer.close ();
		BufferedWriter writer2 = new BufferedWriter (new FileWriter (name + ".txt")); //create username file
		writer2.close ();
		System.out.println ("New Username Created: " + name + "\nBe sure to select ~Save Any Changes(Overwrite)~ when complete.");
		space ();
		System.out.print ("Welcome " + name + "!");
		space ();


		Arrays.fill (songName, null); //clear all existing arrays, new user has empty MP3 collection
		Arrays.fill (artistName, null);
		Arrays.fill (category, null);
		Arrays.fill (year, null);


	    }
	    else if (response == 2)
	    {
		System.out.print ("Username: ");
		name = stdin.readLine ();
		Arrays.fill (songName, null); //clear all existing arrays
		Arrays.fill (artistName, null);
		Arrays.fill (category, null);
		Arrays.fill (year, null);

		BufferedReader reader = new BufferedReader (new FileReader ("Username.txt")); //read username file
		String line = null;
		String[] username = new String [MAX];
		int counter = 0, counter2 = 0;

		while ((line = reader.readLine ()) != null) //open username file to determine current usernames
		{
		    username [counter] = line;
		    counter++;
		}
		reader.close ();
		for (int i = 0 ; i < counter ; i++) //search for existing user
		{
		    if (name.equals (username [i]))
		    {
			System.out.print ("Welcome " + name + "!");
			space ();
			counter2++;
		    }
		}
		if (counter2 == 1) 
		{
		    BufferedReader reader2 = new BufferedReader (new FileReader (name + ".txt")); //read new user file to four arrays

		    while ((line = reader2.readLine ()) != null)
		    {
			songName [count [0]] = line;
			artistName [count [0]] = reader2.readLine ();
			category [count [0]] = reader2.readLine ();
			year [count [0]] = reader2.readLine ();
			count [0]++;
		    }

		    reader2.close ();
		}

		else if (counter2 == 0) //if username not found in username list, create new user
		{
		    space ();
		    System.out.println ("Username Not Found.");
		    space ();

		    BufferedWriter writer = new BufferedWriter (new FileWriter ("username.txt")); //add new username to username list
		    for (int k = 0 ; k < counter ; k++)
		    {
			writer.write (username [k]);
			writer.newLine ();
		    }
		    writer.write (name);
		    writer.close ();
		    BufferedWriter writer2 = new BufferedWriter (new FileWriter (name + ".txt")); //create new username file
		    writer2.close ();
		    System.out.println ("New Username Created: " + name + "\nBe sure to select ~Save Any Changes(Overwrite)~ when complete.");
		    space ();
		    System.out.print ("Welcome " + name + "!");
		    space ();

		}

	    }
	    else if (response == 0) //return to main menu
	    {
	    }
	    else
		System.out.print ("Invalid Response.");
	}
	while (response != 0); //loops back to top/or returns to main menu

    }


    static void playSong () throws IOException
    {
    //allows user to open mp3 file in same folder
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	int selection;
	do
	{
	    System.out.println ("Due To Monetary Constraints And A Limited Budget,\nOnly One Song Can Be Played: Neon Storm\nWould You Like To Play It?\n(1)Yes(0)Return To Main Menu");
	    selection = Integer.parseInt (stdin.readLine ());

	    if (selection == 1) //play song
	    {
		System.out.print ("Enjoy!");
		space ();
		Process variable = Runtime.getRuntime ().exec ("rundll32 url.dll,FileProtocolHandler " + " Neon_Storm.mp3"); //opens mp3 file
	    }
	    else if (selection == 0) //return to main menu
	    {
	    }
	    else
	    {
		System.out.println ("Invalid Input. Please Try Again.");
		space ();
	    }
	}
	while (selection != 0 && selection != 1); //loops back to top/or returns to main menu

    }


    public static void main (String str[]) throws IOException //main method begins
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	DecimalFormat df = new DecimalFormat ("#");

	final int MAX = 100; //initialize variables and arrays
	String[] songName = new String [MAX];
	String[] artistName = new String [MAX];
	String[] category = new String [MAX];
	String[] year = new String [MAX];
	int[] count = new int [1];
	count [0] = 0;
	String name;
	int selection;
	line ();
	System.out.println ("|-----------------------MP3 PLAYER-----------------------|"); //welcome screen
	line ();
	System.out.print ("Welcome to your MP3 collection,\nA place to satisfy your musical affection(s),\nBuckle up tight,\nReady for delight,\nHave no shame,\nEnter your name: ");
	name = stdin.readLine ();
	user (name, songName, artistName, category, year, count, MAX); //open user file, read songs to array

	do //do while loop allows user to revisit main menu
	{
	    space (); //main menu
	    System.out.println ("Menu:\n(1)Open and View Collection\n(2)Add To The Collection\n(3)Search\n(4)Sort\n(5)Delete A Song\n(6)Create Backup\n(7)Save Any Changes(Overwrite)\n(8)Switch User\n(9)Play Music\n(0)Exit");

	    selection = Integer.parseInt (stdin.readLine ());
	    switch (selection) //switch statement allows user to make a choice on main menu
	    {
		case 0:
		    System.out.print ("A time to come,\nIt was fun,\nA time to leave,\nI can't believe,\nProgrammed in Ready,\nLeaving already?\nGoodbye!"); //exit program
		    break;
		case 1:
		    display (songName, artistName, category, year, count); //display MP3 collection of current user
		    break;
		case 2:
		    add (songName, artistName, category, year, count); //add song to MP3 collection of current user
		    break;
		case 3:
		    search (songName, artistName, category, year, count); //search for a song by song name/artist/category/year in MP3 collection of current user
		    break;
		case 4:
		    sort (songName, artistName, category, year, count); //sort by song name/artist/category/year in MP3 collection of current user
		    break;
		case 5:
		    delete (songName, artistName, category, year, count); //delete a song in MP3 collection of current user
		    break;
		case 6:
		    backup (songName, artistName, category, year, count, name, MAX); //create backup of current user's MP3 collection in a new file
		    break;
		case 7:
		    save (songName, artistName, category, year, count, name); //save current user's MP3 collection
		    break;
		case 8:
		    newUser (songName, artistName, category, year, count, MAX); //switch user or create new user with empty collection
		    break;
		case 9:
		    playSong (); //allows user to open and MP3 file to play
		    break;
		default:
		    System.out.println ("Invalid response. Please try again."); //validation for invalid reponses


	    }
	}


	while (selection != 0); //allows user to exit program





    }
}


