// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.vectors.laf;

public class matrix
{
    double[][] m;
    
    public matrix() {
        this.m = new double[][] { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
    }
    
    public matrix(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final double n12, final double n13, final double n14, final double n15, final double n16) {
        this.m = new double[][] { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
        this.setAt(0, 0, n);
        this.setAt(0, 1, n2);
        this.setAt(0, 2, n3);
        this.setAt(0, 3, n4);
        this.setAt(1, 0, n5);
        this.setAt(1, 1, n6);
        this.setAt(1, 2, n7);
        this.setAt(1, 3, n8);
        this.setAt(2, 0, n9);
        this.setAt(2, 1, n10);
        this.setAt(2, 2, n11);
        this.setAt(2, 3, n12);
        this.setAt(3, 0, n13);
        this.setAt(3, 1, n14);
        this.setAt(3, 2, n15);
        this.setAt(3, 3, n16);
    }
    
    public matrix(final matrix matrix) {
        this.m = new double[][] { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.m[i][j] = matrix.m[i][j];
            }
        }
    }
    
    public void compose(final matrix matrix) {
        final matrix postMult = this.postMult(matrix);
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.m[i][j] = postMult.m[i][j];
            }
        }
    }
    
    double deg2rad(final double n) {
        return n * 0.017453292519943295;
    }
    
    public void displayMatrix() {
        for (int i = 0; i < 4; ++i) {
            System.out.print("| ");
            for (int j = 0; j < 4; ++j) {
                System.out.print(this.m[i][j] + " ");
            }
            System.out.println("|");
        }
    }
    
    public double getAt(final int n, final int n2) {
        return this.m[n][n2];
    }
    
    public matrix inverse() {
        final matrix matrix = new matrix(this);
        final matrix matrix2 = new matrix();
        for (int i = 0; i < 4; ++i) {
            final double at = matrix.getAt(i, i);
            for (int j = 0; j < 4; ++j) {
                matrix.setAt(i, j, matrix.getAt(i, j) / at);
                matrix2.setAt(i, j, matrix2.getAt(i, j) / at);
            }
            for (int k = 0; k < 4; ++k) {
                if (k != i) {
                    final double at2 = matrix.getAt(k, i);
                    for (int l = 0; l < 4; ++l) {
                        matrix.setAt(k, l, matrix.getAt(k, l) - at2 * matrix.getAt(i, l));
                        matrix2.setAt(k, l, matrix2.getAt(k, l) - at2 * matrix2.getAt(i, l));
                    }
                }
            }
        }
        return matrix2;
    }
    
    public point3[] postMult(final point3[] array, final int n) {
        final point3[] array2 = new point3[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = this.postMult(array[i]);
        }
        return array2;
    }
    
    public matrix postMult(final matrix matrix) {
        final matrix matrix2 = new matrix();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                matrix2.m[i][j] = this.m[i][0] * matrix.m[0][j] + this.m[i][1] * matrix.m[1][j] + this.m[i][2] * matrix.m[2][j] + this.m[i][3] * matrix.m[3][j];
            }
        }
        return matrix2;
    }
    
    public point3 postMult(final point3 point3) {
        final point3 point4 = new point3();
        point4.setX(point3.x() * this.m[0][0] + point3.y() * this.m[0][1] + point3.z() * this.m[0][2] + point3.w() * this.m[0][3]);
        point4.setY(point3.x() * this.m[1][0] + point3.y() * this.m[1][1] + point3.z() * this.m[1][2] + point3.w() * this.m[1][3]);
        point4.setZ(point3.x() * this.m[2][0] + point3.y() * this.m[2][1] + point3.z() * this.m[2][2] + point3.w() * this.m[2][3]);
        point4.setW(point3.x() * this.m[3][0] + point3.y() * this.m[3][1] + point3.z() * this.m[3][2] + point3.w() * this.m[3][3]);
        point4.normalize();
        return point4;
    }
    
    public vector3 postMult(final vector3 vector3) {
        final point3 postMult = this.postMult(vector3.position().head());
        return new vector3(0.0, 0.0, 0.0, postMult.x(), postMult.y(), postMult.z());
    }
    
    public void rotX(final double n) {
        final double deg2rad = this.deg2rad(n);
        final matrix matrix = new matrix();
        final double sin = Math.sin(deg2rad);
        final double cos = Math.cos(deg2rad);
        matrix.m[1][1] = cos;
        matrix.m[1][2] = -sin;
        matrix.m[2][1] = sin;
        matrix.m[2][2] = cos;
        this.compose(matrix);
    }
    
    public void rotY(final double n) {
        final double deg2rad = this.deg2rad(n);
        final matrix matrix = new matrix();
        final double sin = Math.sin(deg2rad);
        final double cos = Math.cos(deg2rad);
        matrix.m[0][0] = cos;
        matrix.m[0][2] = -sin;
        matrix.m[2][0] = sin;
        matrix.m[2][2] = cos;
        this.compose(matrix);
    }
    
    public void rotZ(final double n) {
        final double deg2rad = this.deg2rad(n);
        final matrix matrix = new matrix();
        final double sin = Math.sin(deg2rad);
        final double cos = Math.cos(deg2rad);
        matrix.m[0][0] = cos;
        matrix.m[0][1] = -sin;
        matrix.m[1][0] = sin;
        matrix.m[1][1] = cos;
        this.compose(matrix);
    }
    
    public void scale(final double n, final double n2, final double n3) {
        final matrix matrix = new matrix();
        matrix.m[0][0] = n;
        matrix.m[1][1] = n2;
        matrix.m[2][2] = n3;
        this.compose(matrix);
    }
    
    public void setAt(final int n, final int n2, final double n3) {
        this.m[n][n2] = n3;
    }
    
    public void translate(final double n, final double n2, final double n3) {
        final matrix matrix = new matrix();
        matrix.m[0][3] = n;
        matrix.m[1][3] = n2;
        matrix.m[2][3] = n3;
        this.compose(matrix);
    }
}
