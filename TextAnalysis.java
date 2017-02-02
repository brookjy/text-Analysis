import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class TextAnalysis
{
	public static String[] wordlist = new String[10000];// I wish I could use list
	public static int[] frequency = new int[10000];// I wish I could use list
	public static int[] wordfreq = new int[10000];// I wish I could use list

	public static int findWord(String[] wordlist, String words){
		int location = 0;
		boolean check = false;
		for (int i = 0; i < wordlist.length; i++)
		{
			if (wordlist[i] != null){
				if(words.compareTo(wordlist[i]) == 0)
				{
					frequency[i]++;
					location = i;
					check = true;
				}
			}
		}
		if(!check)
		{
			for(int i=0; i<wordlist.length; i++)
			{
				if(wordlist[i] == null)
				{
					wordlist[i] = words;
					frequency[i]++;
					break;
				}
			}

		}

		return location;

	}

	public static void main(String[] args) 
	{
		//String[] wordlist = new String[10000];// I wish I could use list
		//int[] frequency = new int[10000];// I wish I could use list
		//int[] wordfreq = new int[10000];// I wish I could use list

		//boolean check = false;
		String fileLocation;
		String words; String longestWord = null;
		int length = 0;
		int wordcount = 0; int longest = 0; int counter = 0;
		int wordlength = 0; int wordLen = 0; int total = 0;

		
		try 
		{
			fileLocation = args[0];
			/*Scan the file from argument*/
			Scanner finput = new Scanner (new FileReader(fileLocation));

			/*Check if the file is empty*/
			if (!finput.hasNext())
			{
				System.out.println("File is empty");
			}

			while(finput.hasNext())
			{	
				/*Inital Variables*/
				//check = false;
				words=finput.next();
				wordcount++;
				words = words.toLowerCase();
				words = words.replace(",","");
				words = words.replace(".","");

				/*Check for the logest word*/
				if(words.length() >= longest)
				{
					longest = words.length();
					longestWord = words;
				}

				/*Check if the word is already in the list*/
				/*Insert the words into the array if not*/
				
				findWord(wordlist, words);

				/*Add into frequency list*/
				wordLen = words.length();
				wordfreq[wordLen-1]++;
			}

			/*Check the length of the wordlist*/
			for(int i=0; i<wordlist.length; i++)
			{
				if(wordlist[i] != null)
				{
					wordlength++;
				}
			}

			/*Finally, Print the out put....*/
			System.out.println("TEXT FILE STATISTICS");
			System.out.println("--------------------");
			System.out.println("Length of longest word: " + longest + " (\"" + longestWord + "\")");
			System.out.println("Number of words in file wordlist: " + wordlength );
			System.out.println("Number of words in file: " + wordcount);
			System.out.println();
			System.out.println("Word-frequency statistics");
			for(int k=0; k<wordfreq.length; k++)
			{
				if(k < 9 )
				{
					System.out.println("	Word-length " + (k+1) +": " + wordfreq[k]);
				}
				else
				{
					total += wordfreq[k];
				}
			}

			System.out.println("	Words-length >= 10: " + total);
			System.out.println();
			System.out.println("Wordlist dump:");
			for(int i=0; i<wordlist.length;i++)
			{
				if (wordlist[i] != null){
					System.out.println(wordlist[i] + ":" + frequency[i]);
				}
			}

		}
		
		catch(FileNotFoundException ex)
		{
		System.out.println("Could not find file");
		}

		

	}


}