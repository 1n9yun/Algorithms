package ps.구현;

import java.util.Arrays;
import java.util.Scanner;

public class boj5373_큐빙 {
    static final int U = 0;
    static final int D = 1;
    static final int F = 2;
    static final int B = 3;
    static final int L = 4;
    static final int R = 5;
    static class Cube{
        Piece[][][] pieces;

        public Cube(char[] colors) {
            pieces = new Piece[3][3][3];
            for(int i=0;i<pieces.length;i++){
                for(int j=0;j<pieces[i].length;j++){
                    for(int k=0;k<pieces[i][j].length;k++){
                        char[] param = new char[6];
                        param[U] = i == 0 ? colors[U] : '0';
                        param[D] = i == 2 ? colors[D] : '0';
                        param[F] = j == 2 ? colors[F] : '0';
                        param[B] = j == 0 ? colors[B] : '0';
                        param[L] = k == 0 ? colors[L] : '0';
                        param[R] = k == 2 ? colors[R] : '0';
                        pieces[i][j][k] = new Piece(param);
                    }
                }
            }
        }

        public void rotate(int side, int dir){
            rotateSide(side, dir);
            for(int i=0;i<pieces.length;i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    for (int k = 0; k < pieces[i][j].length; k++) {
                        if(side == U && i == 0) pieces[i][j][k].rotate(side, dir);
                        else if(side == D && i == 2) pieces[i][j][k].rotate(side, dir);
                        else if(side == F && j == 2) pieces[i][j][k].rotate(side, dir);
                        else if(side == B && j == 0) pieces[i][j][k].rotate(side, dir);
                        else if(side == L && k == 0) pieces[i][j][k].rotate(side, dir);
                        else if(side == R && k == 2) pieces[i][j][k].rotate(side, dir);
                    }
                }
            }
        }
        public void printAnswer(){
            for(int i=0;i<pieces[0].length;i++){
                for(int j=0;j<pieces[0][i].length;j++){
                    System.out.print(pieces[0][i][j].color[U]);
                }
                System.out.println();
            }
        }

        private void rotateSide(int side, int dir){
            if(side == U || side == D) rotateSideUD(side, side == U ? dir : dir * -1);
            else if(side == F || side == B) rotateSideFB(side, side == F ? dir : dir * -1);
            else if(side == L || side == R) rotateSideLR(side, side == L ? dir : dir * -1);
        }
        private void rotateSideUD(int side, int dir){
            final int i = side == U ? 0 : 2;
            if(dir == 1){
                Piece temp = pieces[i][0][0];
                pieces[i][0][0] = pieces[i][2][0];
                pieces[i][2][0] = pieces[i][2][2];
                pieces[i][2][2] = pieces[i][0][2];
                pieces[i][0][2] = temp;

                temp = pieces[i][0][1];
                pieces[i][0][1] = pieces[i][1][0];
                pieces[i][1][0] = pieces[i][2][1];
                pieces[i][2][1] = pieces[i][1][2];
                pieces[i][1][2] = temp;
            }else{
                Piece temp = pieces[i][1][2];
                pieces[i][1][2] = pieces[i][2][1];
                pieces[i][2][1] = pieces[i][1][0];
                pieces[i][1][0] = pieces[i][0][1];
                pieces[i][0][1] = temp;

                temp = pieces[i][0][2];
                pieces[i][0][2] = pieces[i][2][2];
                pieces[i][2][2] = pieces[i][2][0];
                pieces[i][2][0] = pieces[i][0][0];
                pieces[i][0][0] = temp;
            }

        }
        private void rotateSideFB(int side, int dir){
            final int j = side == F ? 2 : 0;
            if(dir == 1){
                Piece temp = pieces[0][j][0];
                pieces[0][j][0] = pieces[2][j][0];
                pieces[2][j][0] = pieces[2][j][2];
                pieces[2][j][2] = pieces[0][j][2];
                pieces[0][j][2] = temp;

                temp = pieces[0][j][1];
                pieces[0][j][1] = pieces[1][j][0];
                pieces[1][j][0] = pieces[2][j][1];
                pieces[2][j][1] = pieces[1][j][2];
                pieces[1][j][2] = temp;
            }else{
                Piece temp = pieces[1][j][2];
                pieces[1][j][2] = pieces[2][j][1];
                pieces[2][j][1] = pieces[1][j][0];
                pieces[1][j][0] = pieces[0][j][1];
                pieces[0][j][1] = temp;

                temp = pieces[0][j][2];
                pieces[0][j][2] = pieces[2][j][2];
                pieces[2][j][2] = pieces[2][j][0];
                pieces[2][j][0] = pieces[0][j][0];
                pieces[0][j][0] = temp;
            }
        }
        private void rotateSideLR(int side, int dir){
            final int k = side == L ? 0 : 2;
            if(dir == 1){
                Piece temp = pieces[0][0][k];
                pieces[0][0][k] = pieces[2][0][k];
                pieces[2][0][k] = pieces[2][2][k];
                pieces[2][2][k] = pieces[0][2][k];
                pieces[0][2][k] = temp;

                temp = pieces[1][0][k];
                pieces[1][0][k] = pieces[2][1][k];
                pieces[2][1][k] = pieces[1][2][k];
                pieces[1][2][k] = pieces[0][1][k];
                pieces[0][1][k] = temp;
            }else{
                Piece temp = pieces[0][2][k];
                pieces[0][2][k] = pieces[2][2][k];
                pieces[2][2][k] = pieces[2][0][k];
                pieces[2][0][k] = pieces[0][0][k];
                pieces[0][0][k] = temp;

                temp = pieces[0][1][k];
                pieces[0][1][k] = pieces[1][2][k];
                pieces[1][2][k] = pieces[2][1][k];
                pieces[2][1][k] = pieces[1][0][k];
                pieces[1][0][k] = temp;
            }
        }

        private static class Piece{
            char[] color;

            public Piece(char[] color) {
                this.color = color;
            }
            public void rotate(int side, int dir){
                if(side == U || side == D) RotateUD(side == U ? dir : dir * -1);
                else if(side == F || side == B) RotateFB(side == F ? dir : dir * -1);
                else RotateLR(side == L ? dir : dir * -1);
            }
            private void RotateUD(int dir){
                if(dir == 1) {
//                    BRFL
                    char temp = color[L];
                    color[L] = color[F];
                    color[F] = color[R];
                    color[R] = color[B];
                    color[B] = temp;
                }else{
                    char temp = color[B];
                    color[B] = color[R];
                    color[R] = color[F];
                    color[F] = color[L];
                    color[L] = temp;
                }
            }
            private void RotateFB(int dir){
                if(dir == 1) {
//                    URDL
                    char temp = color[L];
                    color[L] = color[D];
                    color[D] = color[R];
                    color[R] = color[U];
                    color[U] = temp;
                }else{
                    char temp = color[U];
                    color[U] = color[R];
                    color[R] = color[D];
                    color[D] = color[L];
                    color[L] = temp;
                }
            }
            private void RotateLR(int dir){
                if(dir == 1) {
//                    UFDB
                    char temp = color[B];
                    color[B] = color[D];
                    color[D] = color[F];
                    color[F] = color[U];
                    color[U] = temp;
                }else{
                    char temp = color[U];
                    color[U] = color[F];
                    color[F] = color[D];
                    color[D] = color[B];
                    color[B] = temp;
                }
            }
        }
//
//        void print(){
//            System.out.println(U);
//            for(int j=0;j<3;j++){
//                for(int k=0;k<3;k++){
//                    System.out.print(pieces[0][j][k].color[U]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println(D);
//            for(int j=0;j<3;j++){
//                for(int k=0;k<3;k++){
//                    System.out.print(pieces[2][j][k].color[D]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println(F);
//            for(int i=0;i<3;i++){
//                for(int k=0;k<3;k++){
//                    System.out.print(pieces[i][2][k].color[F]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println(B);
//            for(int i=0;i<3;i++){
//                for(int k=0;k<3;k++){
//                    System.out.print(pieces[i][0][k].color[B]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println(L);
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(pieces[i][j][0].color[L]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println(R);
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(pieces[i][j][2].color[R]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for(int tc=1;tc<=TC;tc++){
            Cube cube = new Cube(new char[]{'w', 'y', 'r', 'o', 'g', 'b'});
            int cnt = sc.nextInt();
            for(int i=0;i<cnt;i++){
                String command = sc.next();

                char side = command.charAt(0);
                char dir = command.charAt(1);

                if(side == 'U') cube.rotate(U, dir == '+' ? 1 : -1);
                else if(side == 'D') cube.rotate(D, dir == '+' ? 1 : -1);
                else if(side == 'F') cube.rotate(F, dir == '+' ? 1 : -1);
                else if(side == 'B') cube.rotate(B, dir == '+' ? 1 : -1);
                else if(side == 'L') cube.rotate(L, dir == '+' ? 1 : -1);
                else if(side == 'R') cube.rotate(R, dir == '+' ? 1 : -1);
//                cube.print();
            }
            cube.printAnswer();
        }
    }
}
