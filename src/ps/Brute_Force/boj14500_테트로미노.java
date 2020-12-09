package ps.Brute_Force;

import java.util.*;

public class boj14500_테트로미노 {
    static int[][][][][] tetromino = {
            {
                    {
                            {
                                    {0, 0}, {0, 1}, {0, 2}, {0, 3}
                            },
                            {
                                    {0, 0}, {1, 0}, {2, 0}, {3, 0}
                            }
                    }
            },
            {
                    {
                            {
                                    {0, 0}, {0, 1}, {1, 0}, {1, 1}
                            }
                    }
            },
            {
                    {
                            {
                                    {0, 0}, {1, 0}, {2, 0}, {2, 1}
                            },
                            {
                                    {0, 0}, {0, 1}, {0, 2}, {1, 0}
                            },
                            {
                                    {0, 0}, {0, 1}, {1, 1}, {2, 1}
                            },
                            {
                                    {0, 2}, {1, 0}, {1, 1}, {1, 2}
                            }
                    },
                    {
                            {
                                    {0, 1}, {1, 1}, {2, 1}, {2, 0}
                            },
                            {
                                    {0, 0}, {1, 0}, {1, 1}, {1, 2}
                            },
                            {
                                    {0, 0}, {0, 1}, {1, 0}, {2, 0}
                            },
                            {
                                    {0, 0}, {0, 1}, {0, 2}, {1, 2}
                            }

                    }
            },
            {
                    {
                            {
                                    {0, 0}, {1, 0}, {1, 1}, {2, 1}
                            },
                            {
                                    {0, 1}, {0, 2}, {1, 0}, {1, 1}
                            }

                    },
                    {
                            {
                                    {0, 1}, {1, 1}, {1, 0}, {2, 0}
                            },
                            {
                                    {0, 0}, {0, 1}, {1, 1}, {1, 2}
                            }
                    }
            },
            {
                    {
                            {
                                    {0, 0}, {0, 1}, {0, 2}, {1, 1},
                            },
                            {
                                    {0, 1}, {1, 0}, {1, 1}, {2, 1},
                            },
                            {
                                    {0, 1}, {1, 0}, {1, 1}, {1, 2},
                            },
                            {
                                    {0, 0}, {1, 0}, {1, 1}, {2, 0}
                            }
                    }
            }
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] paper = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                paper[i][j] = sc.nextInt();
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int[][][][] kind : tetromino) {
            for (int[][][] reflection : kind) {
                for (int[][] dir : reflection) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
//                            System.out.print(i + " " + j + ": ");
                            int sum = 0;

                            for (int[] c : dir) {
                                int row = i + c[0];
                                int col = j + c[1];
                                if (0 <= row && row < n && 0 <= col && col < m) {
                                    sum += paper[row][col];
                                } else {
                                    sum = -1;
                                    break;
                                }
                            }
//                            System.out.println(sum);
                            if (sum != -1) ans = Math.max(ans, sum);
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
