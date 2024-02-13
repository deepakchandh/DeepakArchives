//$Id$
package com.java.Greedy;

import java.util.*;



class Meeting{
	int start;
	int end;
	int pos;
	
	public Meeting(int start, int end, int pos) {
		this.start = start;
		this.end = end;
		this.pos = pos;
	}
}
class MyComp implements Comparator<Meeting>{

	@Override
	public int compare(Meeting o1, Meeting o2) {
		if (o1.end > o2.end) {
			return 1;
		}else if (o1.end < o2.end) {
			return -1;
		}
		return 0;
	}
	
}

public class MeetingGFG{
	
	
	public static void main (String[] args)
	{

		// Starting time
		int s[] = {  3, 0,1, 5, 8, 5 };

		// Finish time
		int f[] = { 4, 6,2, 7, 9, 9 };

		ArrayList<Meeting> meet = new ArrayList<>();
		for(int i = 0; i < s.length; i++)
			meet.add(new Meeting(s[i], f[i], i));
		maxMeeting(meet, meet.size());
	}

	private static void maxMeeting(ArrayList<Meeting> meet, int size) {
		List<Integer> finalList =  new ArrayList<Integer>();

//		Collections.sort(meet, new MyComp());
		
		Collections.sort(meet, new MyComp());
		finalList.add(meet.get(0).pos);
		
		int timeLimit = meet.get(0).end; // 1st index timeLimit
		
		for(int i=1;i<meet.size();i++){
			if (meet.get(i).start > timeLimit) {
				finalList.add(meet.get(i).pos);
				timeLimit = meet.get(i).end; 
			}
		}
		for(int i = 0; i < finalList.size(); i++)
			System.out.print(finalList.get(i) + 1 + " ");
		
	}
}

