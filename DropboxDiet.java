
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author danielgur
 */
public class DropboxDiet
{

    ArrayList<Food> foodItems;
    ArrayList<Exercise> exerciseItems;
    boolean[] sols;

    public static void main(String args[])
    {
        new DropboxDiet(args[0]);
    }

    public DropboxDiet(String fileName)
    {
        foodItems = new ArrayList<Food>();
        exerciseItems = new ArrayList<Exercise>();

        loadData(fileName);

        Collections.sort(foodItems);
        Collections.sort(exerciseItems);

        int a[] = new int[foodItems.size()];

        for (int i = 0; i < foodItems.size(); i++)
        {
            a[i] = foodItems.get(i).getCals();
        }

        sols = new boolean[a.length];
        boolean foundSol = false;
        for (int i = 0; i < exerciseItems.size(); i++)
        {
            setSolsFalse();		//set solution boolean array all to false
            boolean result = displaySubset(a, -1 * exerciseItems.get(i).getCals(), exerciseItems.get(i).getName());		//find a subset if one exists
            if (result)
            {
                foundSol = true;
            }
        }

        if (!foundSol)
        {
            System.out.println("No Solution");
        }
    }

    public void setSolsFalse()
    {
        for (int i = 0; i < sols.length; i++)
        {
            sols[i] = false;
        }
    }

    public void loadData(String fileName)
    {
        try
        {
            BufferedReader fromTextFile = null;

            fromTextFile = new BufferedReader(new FileReader(fileName));

            String line = "";
            while ((line = fromTextFile.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, " ");
                //ignore line unless its split into 2 tokens
                if (st.countTokens() == 2)
                {
                    String name = st.nextToken();
                    int cals = Integer.parseInt(st.nextToken());
                    //split entries into an exercise or a food object
                    if (cals < 0)
                    {
                        exerciseItems.add(new Exercise(name, cals));
                    } else
                    {
                        foodItems.add(new Food(name, cals));
                    }
                }
            }
            fromTextFile.close();
        } catch (FileNotFoundException f)
        {
            f.printStackTrace();
        } catch (IOException io)
        {
            io.printStackTrace();
        }
    }

    public boolean displaySubset(int set[], int target, String exerciseName)
    {
        return displaySubset(set, target, 0, false, exerciseName);
    }

    public boolean displaySubset(int set[], int target, int index, boolean inp, String exerciseName)
    {
        //if the target is ever less than 0 the recursion has to be stopped, as it is no longer a valid subset
        if (target < 0)
        {
            return false;
        }
        //set the matching element in the boolean array to the correct value
        if (index <= set.length && index > 0)
        {
            sols[index - 1] = inp;
        }

        //the solution has been found when the target is 0
        if (target == 0)
        {
            //Descriptive output
            //System.out.println("Activity: \n\t"+exerciseName+"\nRewards:");
            System.out.println(exerciseName);
            for (int i = 0; i < sols.length; i++)
            {
                if (sols[i] == true)
                {
                    //Descriptive output
                    //System.out.println("\t"+foodItems.get(i).getName());
                    //Display the subset
                    System.out.println(foodItems.get(i).getName());

                }
            }
            System.out.println("");
            return true;
        }

        //If the end of the set has come and the index is positive this will occur 
        if (set.length == index)
        {
            return (target == 0);
        } //else recursively call the function twice, one instance will subtract the current target with the elements of the set at the index, 
        //and the other call will ignore the current target incase that element is not needed in the subset sum
        else
        {
            return displaySubset(set, target - set[index], index + 1, true, exerciseName) || displaySubset(set, target, index + 1, false, exerciseName);
        }

    }
}

//Excercise class is used to store the exercises which are represented with a -ve int in the text file. 
class Exercise implements Comparable<Exercise>
{

    private String name;
    private int cals;

    public Exercise(String n, int c)
    {
        this.name = n;
        this.cals = c;
    }

    public Exercise()
    {
        name = "";
        cals = -1;
    }

    public String getName()
    {
        return name;
    }

    public int getCals()
    {
        return cals;
    }

    public int compareTo(Exercise anotherExercise)
    {
        return this.name.compareTo(anotherExercise.getName());
    }
}

//Food class is used to store the foods which are represented with a +ve int in the text file. 
class Food implements Comparable<Food>
{

    private String name;
    private int cals;

    Food(String n, int c)
    {
        this.name = n;
        this.cals = c;
    }

    Food()
    {
        name = "";
        cals = -1;
    }

    public String getName()
    {
        return name;
    }

    public int getCals()
    {
        return cals;
    }

    public int compareTo(Food anotherFood)
    {
        return this.name.compareTo(anotherFood.getName());
        //By using the calories rather than the name for the food it would be more optimised
        //for the searching, but since the upper bound is 50 it doesn't effect the running much
    }
}
