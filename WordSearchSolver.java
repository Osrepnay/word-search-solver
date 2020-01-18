import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
public class WordSearchSolver{
	//NOTE: arrays are using [row][column] instead of [column][row] 
	static char[][] puzzle;
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the rows of the word search with no spaces in between the letters, and input an empty line to stop.");
		ArrayList<char[]> puzzleTemp=new ArrayList<char[]>();
		while(true){
			String line=s.nextLine();
			if(line.equals("")) {
				break;
			}
			puzzleTemp.add(line.toCharArray());
		}
		puzzle=new char[puzzleTemp.size()][puzzleTemp.get(0).length];
		Collections.reverse(puzzleTemp);
		for(int i=0; i<puzzleTemp.size(); i++){
			puzzle[i]=puzzleTemp.get(i);
		}
		System.out.println("Enter the words you want to find, line by line, and input an empty line to stop:");
		while(true){
			String word=s.nextLine();
			if(word.equals("")){
				break;
			}
			char[] wordArray=word.toCharArray();
			int[][] positions=new int[wordArray.length][2];
			int[][] potentialPositions=new int[wordArray.length][2];
			int[] startingLetter=new int[2];
			search:
			for(int i=0; i<puzzle.length; i++){
				for(int j=0; j<puzzle[0].length; j++){
					if(puzzle[i][j]!=wordArray[0]){
						continue;
					}
					char[] wordArrayEnd=Arrays.copyOfRange(wordArray, 1, wordArray.length);
					int[] pos=new int[]{i, j};
					for(int l=1; l<=8; l++){
						if(search(wordArrayEnd, pos, l)){
							System.out.println("Starting Letter(x, y): "+pos[1]+", "+pos[0]);
							break search;
						}
					}
				}
			}
		}
	}
	//direction 1=left, 2=left-up, 3=up, etc.
	public static boolean search(char[] word, int[] pos, int direction){
		boolean containsWord=false;
		switch(direction){
			case 1:
				if(pos[1]-1>=0 && puzzle[pos[0]][pos[1]-1]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0], pos[1]-1}, direction)){
						return true;
					}
				}
				break;
			case 2:
				if(pos[0]+1<puzzle.length && pos[1]-1>=0 && puzzle[pos[0]+1][pos[1]-1]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0]+1, pos[1]-1}, direction)){
						return true;
					}
				}
				break;
			case 3:
				if(pos[0]+1<puzzle.length && puzzle[pos[0]+1][pos[1]]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0]+1, pos[1]}, direction)){
						return true;
					}
				}
				break;
			case 4:
				if(pos[0]+1<puzzle.length && pos[1]+1<puzzle[0].length && puzzle[pos[0]+1][pos[1]+1]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0]+1, pos[1]+1}, direction)){
						return true;
					}
				}
				break;
			case 5:
				if(pos[1]+1<puzzle[0].length && puzzle[pos[0]][pos[1]+1]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0], pos[1]+1}, direction)){
						return true;
					}
				}
				break;
			case 6:
				if(pos[0]-1>=0 && pos[1]+1<puzzle[0].length && puzzle[pos[0]-1][pos[1]+1]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0]-1, pos[1]+1}, direction)){
						return true;
					}
				}
				break;
			case 7:
				if(pos[0]-1>=0 && puzzle[pos[0]-1][pos[1]]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0]-1, pos[1]}, direction)){
						return true;
					}
				}
				break;
			case 8:
				if(pos[0]-1>=0 && pos[1]-1>=0 && puzzle[pos[0]-1][pos[1]-1]==word[0]){
					if(word.length<=1){
						return true;
					}
					if(search(Arrays.copyOfRange(word, 1, word.length), new int[]{pos[0]-1, pos[1]-1}, direction)){
						return true;
					}
				}
				break;
		}
		return false;
	}
}
