/*
 * I strongly suggest you to read these documents mentioned below before diving into code:
 * CHECK THIS OUT:   https://introcs.cs.princeton.edu/java/assignments/atomic.html
 *                   https://justintranjt.me/projects/2017-07-29-avogadro-estimation/
 */

/*
 * here we estimate the Boltzmann's Constant and Avogadro's Constant by the given Beads movement.
 */

import java.util.Scanner;

public class Avogadro {

    private double Square_Sum;
    private int counter = 0;
    private double Meter_to_pixel = 0.000000175; //we will use this to convert pixels distance to meter.

    //here we get the distance and calculate it's square
    public void add( double Distance )
    {
        Square_Sum += Math.pow( Distance * Meter_to_pixel ,2);
        counter++;
    }

    //here is the calculation part just some bunch of formulas
    public void NA ( double Square_Sum, int nBead ){

        double D_CONSTANT = Square_Sum / (2 * nBead);

        double PI = Math.PI;
        double VISCOSITY = 0.0009135;
        double RADIUS = 0.0000005;
        double TEMP = 297;
        double GAS_CONSTANT = 8.31446;

        double boltzmann = (6 * D_CONSTANT * PI * RADIUS * VISCOSITY) / TEMP;
        double avogadro = GAS_CONSTANT / boltzmann;

        System.out.println("Boltzmann = " + String.format("%.4e", boltzmann));
        System.out.println("Avogadro = " + String.format("%.4e", avogadro));
    }


    public static void main( String[] args ){

        Scanner input = new Scanner( System.in );   //here we read the inputs
        Avogadro obj = new Avogadro();
        String tmp;
        while( input.hasNextLine() )
        {
            tmp = input.nextLine();
            if( !tmp.isEmpty() )
            {
                obj.add( Double.parseDouble( tmp  ) );
            }
        }
        input.close();
        obj.NA( obj.Square_Sum, obj.counter );


    }
}

