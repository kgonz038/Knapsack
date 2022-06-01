import java.util.*;

/**
 * Kimberly Gonzalez
 *
 * This program takes user input to get Knapsack info like: max capacity weight, total # of items, value of each item,
 * and the weight of the items. Using the info entered by the user, it will be used in both implementations of the
 * Knapsack algorithms, both 0/1 Knapsack Dynamic and Fractional Knapsack Greedy.
 */
public class knapsack_kimberly_gonzalez
{
    public static void main(String args[])
    {
        Scanner inp = new Scanner(System.in);

        System.out.println("\nInput max weight capacity: ");
        int cap = inp.nextInt(); //user max cap weight

        System.out.println("Enter total number of items in the knapsack: ");
        int numItems = inp.nextInt(); //user amount of items

        int valItems[] = new int[numItems]; //array that'll contain value of each item

        int weight[] = new int[numItems]; //array that'll contain weight of each item

        char letterV = 'A';

        //inserts user item value input by the amount of items entered
        for (int i = 0; i < valItems.length; i++)
        {
            System.out.print("Enter the value of item " + letterV + ": ");

            valItems[i] = inp.nextInt();//read process sizes to array
            letterV++;
        }

        System.out.println("");

        char letterW = 'A';

        //inserts user item weight input by the amount of items entered
        for (int k = 0; k < valItems.length; k++)
        {
            System.out.print("Enter weight of item " + letterW + ": ");

            weight[k] = inp.nextInt();//read process sizes to array

            letterW++;
        }

        //displays information entered and the max values for each algorithm
        System.out.println("-----------------------------------------------------");
        System.out.println("Number of items in the knapsack is " + numItems + "\nValues of the items are " + Arrays.toString(valItems) +
                "\nWeights of the items are " + Arrays.toString(weight) + "\nMax Weight Capacity is " + cap);
        System.out.println("\n0/1 Dynamic Knapsack Max Value is: $" + knapSackDyn(cap, weight, valItems, numItems));
        knapSackGreed(weight, valItems, cap);
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Sorts or Ranks the items, then finds best value to add without going over max cap weight, it it does then gets
     * the items fractional value per pound to add to knapsack
     *
     * @param w weights of items
     * @param v value of items
     * @param W capacity weight of knapsack
     */
    private static void knapSackGreed(int[] w, int[] v, int W )
    {
        int numItems = w.length;
        int j = 0;
        double maxVal = 0;

        //Ranks the items by sorting
        for(int i = numItems - 1; i >= 0; i--)
        {
            for(int k = 0; k < i; k++)
            {
                //compares fractional value, saves the greater value
                if((v[k] / (double) w[k]) < (v[k+1] / (double)w[k+1]))
                {
                    int greater = w[k];

                    w[k] = w[k+1];

                    w[k+1] = greater;

                    greater = v[k];

                    v[k] = v[k+1];

                    v[k+1] = greater;
                }
            }
        }

        //calculates max value
        while(W > 0)
        {
            //while capacity weight is bigger than current item weight
            if(W >= w[j])
            {
                //will subtract item weight amount from cap weight & the value of the current item is added to the max value so far
                W -= w[j];
                maxVal += v[j];
                j++;
            }
            else //otherwise capacity weight is smaller than current item weight
            {
                //gets item value per pound(weight) to get it's fractional value
                maxVal += (v[j] / (double) w[j]) * W;
                W = 0;
            }
        }
        System.out.println("\nFractional Greedy Knapsack Max Value is: $" + Math.round(maxVal));
    }

    /**
     * Calculates the max value using the 0/1 dynamic algorithm of the knapsack problem
     *
     * @param cap max weight capacitcty
     * @param w weight of items
     * @param v Value of items
     * @return
     */
    public static int knapSackDyn(int cap, int[] w, int[] v, int numItems)
    {
        int[][] load = new int[numItems][cap+1];
        int iter = 0; //iterations run for while loop

        //runs as long as i iterator does not go over the total number of items
        while (iter <= numItems-1)
        {
            //runs as long as W is not bigger or equal to max weight capacity
            for (int track = 0; cap >= track; track ++)// track keeps track of max weight
            {
                //starting is 0
                if ( track == 0 || iter == 0)
                    load[iter][track] = 0;

                    //if wight is less than or equal to W
                else if (track >= w[iter])
                    load[iter][track] = Math.max(load[iter - 1][track], load[iter - 1][track - w[iter]] + v[iter]);

                    //if each weight is bigger than W
                else if (w[iter] > track)
                    load[iter][track] = load[iter - 1][track];
            }
            iter++; //iterates i in order to stop while loop

        }
        return load[numItems-1][cap];
    }




}





