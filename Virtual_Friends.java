/* 
After writing my entire program in Java,
I found out that Java Scanner class is too slow that it fails on Kattis even though the program itself is efficient enough.
Thus, I used 'FastReader' class from https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
I got RunTimeError when using Scanner class, but it passed with 'FastReader.'
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Friend {
    int totalNum;
    HashMap<String,Integer> friends;
    int[] repIndex;
    int[] numFriends;

    Friend(int number) {
        if (number > 0){
            totalNum = 0;
            friends = new HashMap<String, Integer>();
            repIndex = new int[number*2];
            numFriends = new int[number*2];
        }
    }

    public int findRepIndex(int myIndex) {
        if (repIndex[myIndex] == myIndex ) {
            return myIndex;
        } else {
            repIndex[myIndex] = findRepIndex(repIndex[myIndex]);
            return repIndex[myIndex];
        }
    }

    public int union(int indexOne, int indexTwo){
        int repOne = findRepIndex(indexOne);
        int repTwo = findRepIndex(indexTwo);

        if (repOne == repTwo) {
            return repOne;
        } else if (numFriends[repOne] > numFriends[repTwo]) {
            numFriends[repOne] = numFriends[repOne] + numFriends[repTwo];
            repIndex[repTwo] = repOne;
            return repOne;
        } else {
            numFriends[repTwo] = numFriends[repOne] + numFriends[repTwo];
            repIndex[repOne] = repTwo;
            return repTwo;
        }
    }
    
    
    /* 
    The following 'FastReader' class is from 
    https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
     */
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }

    public static void main(String[] args) {
        FastReader sc = new FastReader();;
        int numTests = sc.nextInt();

        for (int i=0; i<numTests; i++) {
            int numRelations = sc.nextInt();
            Friend myFriend = new Friend(numRelations);

            for (int j=0; j<numRelations; j++) {

                String[] input = new String[2];
                input[0] = sc.next();
                input[1] = sc.next();

                for (String name : input) {

                    if (! myFriend.friends.containsKey(name)) {
                        myFriend.friends.put(name, myFriend.totalNum);
                        myFriend.totalNum++;
                        myFriend.repIndex[myFriend.friends.size()-1] = myFriend.friends.size()-1;
                        myFriend.numFriends[myFriend.friends.size()-1] = 1;
                    }
                }

                int newRepIndex = myFriend.union(myFriend.friends.get(input[0]), myFriend.friends.get(input[1]));
                System.out.println(myFriend.numFriends[newRepIndex]);

            }
        }
    }
}
