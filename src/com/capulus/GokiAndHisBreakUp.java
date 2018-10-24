package com.capulus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Goki recently had a breakup, so he wants to have some more friends in his life. Goki has N people who he can be friends with, so he decides to choose among them according to their skills set Yi(1<=i<=n). He wants atleast X skills in his friends.
Help Goki find his friends.

 INPUT
First line of the input contains an integer N denoting the number of people.

Next line contains a single integer X - denoting the minimum skill required to be Goki's friend.

Next n lines contain one integer Y - denoting the skill of ith person.

OUTPUT
For each person print if he can be friend with Goki. 'YES' (without quotes) if he can be friends with Goki else 'NO' (without quotes).

CONSTRAINTS

1<=N<=1000000
1<=X,Y<=1000000

SAMPLE INPUT
5
100
110
130
90
100
45
SAMPLE OUTPUT
YES
YES
NO
YES
NO
Explanation
X is 100, so the first query is 110, so as 110 is greater than 100 answer is YES.

 */
public class GokiAndHisBreakUp {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfPeople = Integer.parseInt(br.readLine());
        int minimumSkill = Integer.parseInt(br.readLine());
        List<Integer> skillOfPersonList = new ArrayList<>();
        for(int i=0;i<numberOfPeople;i++){
            skillOfPersonList.add(Integer.parseInt(br.readLine()));
        }
        long start = System.nanoTime();
        //parallel processing
        int numberOfThreads = 4;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        List<Callable<List<String>>> workerList = new ArrayList<>();
        List<List<Integer>> partitions = divideListBasedOnThreads(numberOfThreads,skillOfPersonList,numberOfPeople);
        for(List<Integer> dividedSkillList: partitions){
            workerList.add(new DetermineFriendBasedOnSkillWorker(dividedSkillList,minimumSkill));
        }

        List<Future<List<String>>> futures = service.invokeAll(workerList);
        for(Future<List<String>> result:futures){
            for(String s:result.get()){
                System.out.println(s);
            }
        }
        service.shutdown();

        long end = System.nanoTime();
        UtilsFunction.computeTimeTakenMillSec(start,end);

        //serial processing
        start = System.nanoTime();
        for (int skill:skillOfPersonList){
            if(skill>=minimumSkill){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
        end = System.nanoTime();
        System.out.print(" Serial time taken : ");
        UtilsFunction.computeTimeTakenMillSec(start,end);
    }

    public static <T> List<List<T>> divideListBasedOnThreads(int numberOfThreads,List<T> objectList,int size){
        int partitionSize = (int) size / numberOfThreads;
        List<List<T>> partitions = new ArrayList<>();
        for (int i=0;i<size;i+=partitionSize){
            partitions.add(objectList.subList(i,Math.min(i+partitionSize,size)));
        }
        return partitions;
    }
}

class DetermineFriendBasedOnSkillWorker implements Callable<List<String>> {

    private List<Integer> skillsList = new ArrayList<>();
    private int minimumSkill;

    public DetermineFriendBasedOnSkillWorker(List<Integer> skillsList,int minimumSkill){
        this.skillsList = skillsList;
        this.minimumSkill = minimumSkill;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> resultList = new ArrayList<>();
        for (int skill:skillsList){
            if(skill>=minimumSkill){
                resultList.add("YES");
            }else{
                resultList.add("NO");
            }
        }
        return resultList;
    }
}
