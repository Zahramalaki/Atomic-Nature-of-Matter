/*
 * I strongly suggest you to read these documents mentioned below before diving into code:
 * CHECK THIS OUT:   https://introcs.cs.princeton.edu/java/assignments/atomic.html
 *                   https://justintranjt.me/projects/2017-07-29-avogadro-estimation/
 */

/*
 * here we will track beads and calculate the distance of them,
 * within every two images.
 */

import java.util.Collections;
import java.util.LinkedList;

public class BeadTracker {

    public static void main( String[] args )
    {
        //here we get args from command line
        int min = Integer.parseInt( args[0] );
        double tau = Double.parseDouble( args[1] );
        double Delta = Double.parseDouble( args[2] );

        double Distance;
        LinkedList<Double> Distance_List = new LinkedList<Double>();

        //here we get 2 images and calculate the beads movements and so on...
        for( int i = 0; i < args.length - 4; i++ )
        {
            Picture First_img = new Picture( args[ i + 3 ] );
            Picture Secondary_img = new Picture( args[ i + 4 ] );
            BeadFinder First_Obj = new BeadFinder( First_img, tau );
            BeadFinder Secondary_Obj = new BeadFinder( Secondary_img, tau );
            Blob [] First_BeadList = First_Obj.getBeads( min );
            Blob [] Secondary_BeadList = Secondary_Obj.getBeads( min );

            /*
             * Here we are going to calculate every possible movement of a bead and save it in
             * a LinkedList then the min of it's elements will be the most possible movement.
             */
            for( int i1 = 0; i1 < First_BeadList.length; i1++ )
            {
                for( int i2 = 0; i2 < Secondary_BeadList.length; i2++ )
                {
                    Distance = First_BeadList[ i1 ].distanceTo( Secondary_BeadList[ i2 ] );
                    Distance_List.add( Distance );
                }

                if( Collections.min( Distance_List ) < Delta  )
                {
                    System.out.format( "%.4f\n", Collections.min( Distance_List ) );
                }
                Distance_List.clear();
            }
            System.out.println();
        }
    }
}

