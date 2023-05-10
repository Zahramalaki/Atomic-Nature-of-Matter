/*
 * Well... . Here we created a helper data type "Blob" that will make things easy.
 * This data type is going to store Blob's data such as it's mass and coordinates of central mass.
 */
public class Blob {

    private double x_pos;   //Blob's X central mass.
    private double y_pos;   //Blob's Y central mass.
    private double Sumi;    //sum of X-coordinates of pixels.
    private double Sumj;    //sum of Y-coordinates of pixels.
    private int mass;       //Blob's mass or in other words The number of pixels.

    //here we creat an empty Blob
    public Blob( )
    {

    }

    //here we add every pixels x and y coordinations and then return it's central mass coordinations.
    public void add( int x, int y )
    {
        Sumi += x;
        Sumj += y;

        mass++;

        x_pos = Sumi / this.mass();
        y_pos = Sumj / this.mass();
    }

    //this method returns Blob's mass or the number of pixels in it.
    public int mass()
    {
        return mass;
    }

    //here we calculate the distance between two Blobs. Just some simple Geometry...
    public double distanceTo( Blob that )
    {
        double x_distance = that.x_pos - x_pos;
        double y_distance = that.y_pos - y_pos;

        return Math.sqrt( Math.pow( x_distance, 2 ) + Math.pow( y_distance, 2 ) );
    }

    /*
     * here we return a string containing Blob's mass and coordinations of centeral mass
     * like this format :       ## (###.####, ###.####)     mass (x_pos, y_pos)
     */
    public String toString()
    {
        return String.format( "%2d (%8.4f, %8.4f)", this.mass(), x_pos, y_pos );
    }
}