/*
 * I strongly suggest you to read these documents mentioned below before diving into code:
 *      CHECK THIS OUT:   https://introcs.cs.princeton.edu/java/assignments/atomic.html
 *                        https://justintranjt.me/projects/2017-07-29-avogadro-estimation/
 */

/*
 * here we analyze the given images and get it's mass and coordinations of central mass and save it.
 */

import java.awt.Color;
import java.util.LinkedList;

public class BeadFinder {

    private Blob blob;
    private LinkedList<Blob> BlobList = new LinkedList<Blob>();;

    public BeadFinder( Picture img, double tau )
    {
        /*
         * Here we just clean the noises in the given images...
         * We separate the pixels into foreground and background components like an 2D array of 0, 1.
         * We create a new image and color it's pixels with white and black
         */
        Picture newimg = new Picture( img.width(), img.height() );

        for ( int col = 0; col < img.width(); col++ )
        {
            for ( int row = 0; row < img.height(); row++ )
            {
                Color color = img.get( col, row );
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if ( r < tau && g < tau && b < tau )
                {
                    Color black = new Color(0, 0, 0);
                    newimg.setRGB( col, row, black.getRGB() );
                }
                else
                {
                    Color white = new Color( 255, 255, 255 );
                    newimg.setRGB( col, row, white.getRGB() );
                }
            }
        }

        /*
         *  you may wonder WHAT IS CHECKVALUE? WHAT DOES IT DO?
         *  Let me explain....
         *  If I want to say it quickly and simply, it checks if the BlobExplorer() already
         *  checked that pixel or not. We don't want our BlobExplorer() to check a pixel
         *  multiple times, don't we?
         */
        boolean [][] CheckValue = new boolean [ newimg.width() ][ newimg.height() ];

        /*
         *  Here is the Explorer part. so what does it do?
         *  It goes throw the image and checks it pixel by pixel until it reachs the white pixel, it
         *  calls the BlobExplorer() to check it out!
         */
        for ( int col = 0; col < newimg.width(); col++ )
        {
            for ( int row = 0; row < newimg.height(); row++ )
            {
                if ( newimg.getRGB( col, row ) == -1 && CheckValue[ col ][ row ] == false )
                {
                    blob = new Blob();
                    BlobExplorer( newimg, col, row, CheckValue );
                    BlobList.add( blob );
                }
            }
        }
    }

    //here we filter the BlobList according to given "min" by user and turn it to BeadList.
    public Blob[] getBeads( int min )
    {
        Blob tmp = new Blob();
        for( int i = 0; i < BlobList.size(); i++ )
        {
            tmp = BlobList.get(i);
            if( tmp.mass() < min ){
                BlobList.remove( i );
                i--;
            }
        }

        Blob [] BeadList = BlobList.toArray( new Blob[ 0 ] );

        return BeadList;
    }

    //just a recursion...
    private void BlobExplorer( Picture img, int col, int row, boolean [][] CheckValue )
    {
        // 🢁 check
        if( 0 < row - 1 && row - 1 < img.height() && img.getRGB(col, row - 1) == -1 && CheckValue[ col ][ row - 1 ] == false )
        {
            blob.add( col, row - 1 );
            CheckValue[ col ][ row - 1 ] = true;
            BlobExplorer( img, col, row - 1, CheckValue );
        }

        // 🢅 check
        if( 0 < row - 1 && row - 1 < img.height() && 0 < col + 1 && col + 1 < img.width() && img.getRGB(col + 1, row - 1) == -1 && CheckValue[ col + 1 ][ row - 1 ] == false )
        {
            blob.add( col + 1, row - 1 );
            CheckValue[ col + 1 ][ row - 1 ] = true;
            BlobExplorer( img, col + 1, row - 1, CheckValue );
        }

        // 🢂 check
        if( 0 < col + 1 && col + 1 < img.width() && img.getRGB(col + 1, row) == -1 && CheckValue[ col + 1 ][ row ] == false )
        {
            blob.add( col + 1, row );
            CheckValue[ col + 1 ][ row ] = true;
            BlobExplorer( img, col + 1, row, CheckValue );
        }

        // 🢆 check
        if( 0 < row + 1 && row + 1 < img.height() && 0 < col + 1 && col + 1 < img.width() && img.getRGB(col + 1, row + 1) == -1 && CheckValue[ col + 1 ][ row + 1 ] == false )
        {
            blob.add( col + 1, row + 1 );
            CheckValue[ col + 1 ][ row + 1 ] = true;
            BlobExplorer( img, col + 1, row + 1, CheckValue );
        }

        // 🢃 check
        if( 0 < row + 1 && row + 1 < img.height() && img.getRGB(col, row + 1) == -1 && CheckValue[ col ][ row + 1 ] == false )
        {
            blob.add( col, row + 1 );
            CheckValue[ col ][ row + 1 ] = true;
            BlobExplorer( img, col, row + 1, CheckValue );
        }

        // 🢇 check
        if( 0 < row + 1 && row + 1 < img.height() && 0 < col - 1 && col - 1 < img.width() && img.getRGB(col - 1, row + 1) == -1 && CheckValue[ col - 1 ][ row + 1 ] == false )
        {
            blob.add( col - 1, row + 1 );
            CheckValue[ col - 1 ][ row + 1 ] = true;
            BlobExplorer( img, col - 1, row + 1, CheckValue );
        }

        // 🢀 check
        if( 0 < col - 1 && col - 1 < img.width() && img.getRGB(col - 1, row) == -1 && CheckValue[ col - 1 ][ row ] == false )
        {
            blob.add( col - 1, row );
            CheckValue[ col - 1 ][ row ] = true;
            BlobExplorer( img, col - 1, row, CheckValue );
        }

        // 🢄 check
        if( 0 < row - 1 && row - 1 < img.height() && 0 < col - 1 && col - 1 < img.width() && img.getRGB(col - 1, row - 1) == -1 && CheckValue[ col - 1 ][ row - 1 ] == false )
        {
            blob.add( col - 1, row - 1 );
            CheckValue[ col - 1 ][ row - 1 ] = true;
            BlobExplorer( img, col - 1, row - 1, CheckValue );
        }
    }



    public static void main( String[] args )
    {
        //here we get args from command line
        int min = Integer.parseInt( args[ 0 ] );
        double tau = Double.parseDouble( args[ 1 ] );
        Picture img = new Picture( args[ 2 ] );

        BeadFinder beadfinder = new BeadFinder( img, tau );
        Blob [] BeadList = beadfinder.getBeads( min );
        for( int i = 0; i < BeadList.length; i++ )
        {
            System.out.println( BeadList[i].toString() );
        }
    }
}
