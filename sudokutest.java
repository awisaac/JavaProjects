/*  My output

Author: Alfred E. Newman
Valid start board.
800 | 000 | 042
007 | 059 | 063
000 | 000 | 900
---------------
000 | 900 | 400
650 | 080 | 071
004 | 003 | 000
---------------
002 | 000 | 000
910 | 620 | 800
780 | 000 | 004

Solution found.
895 | 316 | 742
427 | 859 | 163
361 | 247 | 985
---------------
138 | 975 | 426
659 | 482 | 371
274 | 163 | 598
---------------
542 | 738 | 619
913 | 624 | 857
786 | 591 | 234

Valid start board.
000 | 040 | 000
800 | 000 | 007
003 | 060 | 540
---------------
090 | 100 | 800
050 | 304 | 020
007 | 008 | 060
---------------
024 | 000 | 010
001 | 000 | 003
000 | 020 | 000

Solution found.
275 | 943 | 186
846 | 512 | 397
913 | 867 | 542
---------------
492 | 176 | 835
658 | 394 | 721
137 | 258 | 469
---------------
724 | 635 | 918
561 | 489 | 273
389 | 721 | 654

Valid start board.
000 | 000 | 010
042 | 006 | 700
000 | 108 | 000
---------------
005 | 000 | 406
600 | 307 | 009
209 | 000 | 107
---------------
000 | 509 | 000
008 | 700 | 500
030 | 040 | 000

Solution found.
893 | 274 | 615
142 | 956 | 783
756 | 138 | 294
---------------
375 | 891 | 426
614 | 327 | 859
289 | 465 | 137
---------------
427 | 589 | 361
968 | 713 | 542
531 | 642 | 978

Valid start board.
004 | 060 | 500
300 | 200 | 007
600 | 503 | 800
---------------
908 | 000 | 300
000 | 010 | 000
007 | 000 | 401
---------------
003 | 600 | 009
800 | 009 | 004
006 | 080 | 200

Not done yet.
004 | 060 | 503
300 | 200 | 007
670 | 503 | 802
---------------
918 | 000 | 306
000 | 010 | 008
007 | 000 | 401
---------------
003 | 600 | 089
800 | 009 | 004
096 | 080 | 205

Valid start board.
057 | 043 | 000
809 | 000 | 000
030 | 005 | 090
---------------
002 | 071 | 000
004 | 000 | 200
008 | 500 | 900
---------------
040 | 300 | 020
000 | 000 | 401
000 | 720 | 360

Not done yet.
057 | 943 | 000
809 | 067 | 000
430 | 085 | 090
---------------
002 | 471 | 000
004 | 090 | 200
008 | 532 | 900
---------------
040 | 310 | 020
003 | 050 | 401
000 | 724 | 360

Valid start board.
030 | 008 | 050
020 | 090 | 000
000 | 070 | 004
---------------
190 | 002 | 067
280 | 000 | 035
640 | 100 | 098
---------------
500 | 030 | 000
000 | 060 | 040
060 | 900 | 080

Solution found.
736 | 418 | 952
421 | 593 | 876
859 | 276 | 314
---------------
195 | 382 | 467
287 | 649 | 135
643 | 157 | 298
---------------
518 | 734 | 629
972 | 861 | 543
364 | 925 | 781

Valid start board.
000 | 020 | 130
000 | 070 | 004
073 | 000 | 602
---------------
009 | 687 | 000
000 | 000 | 000
000 | 542 | 700
---------------
805 | 000 | 290
300 | 050 | 000
924 | 010 | 000

Not done yet.
090 | 020 | 137
000 | 070 | 904
073 | 090 | 602
---------------
009 | 687 | 300
700 | 931 | 000
030 | 542 | 709
---------------
815 | 764 | 293
367 | 259 | 000
924 | 010 | 576

Valid start board.
704 | 200 | 308
020 | 030 | 090
000 | 000 | 000
---------------
900 | 000 | 600
030 | 060 | 010
008 | 000 | 007
---------------
000 | 000 | 000
060 | 010 | 040
805 | 006 | 702

Not done yet.
704 | 201 | 368
026 | 030 | 090
080 | 600 | 270
---------------
901 | 000 | 603
030 | 060 | 010
608 | 103 | 007
---------------
000 | 000 | 106
060 | 010 | 040
815 | 006 | 732

 */

public class sudokutest {

    public static void main(String[] args) {
        int s[][][] = {
                {
                        { 8, 0, 0, 0, 0, 0, 0, 4, 2 }, // 1
                        { 0, 0, 7, 0, 5, 9, 0, 6, 3 },
                        { 0, 0, 0, 0, 0, 0, 9, 0, 0 },
                        { 0, 0, 0, 9, 0, 0, 4, 0, 0 },
                        { 6, 5, 0, 0, 8, 0, 0, 7, 1 },
                        { 0, 0, 4, 0, 0, 3, 0, 0, 0 },
                        { 0, 0, 2, 0, 0, 0, 0, 0, 0 },
                        { 9, 1, 0, 6, 2, 0, 8, 0, 0 },
                        { 7, 8, 0, 0, 0, 0, 0, 0, 4 } },

                {
                        { 0, 0, 0, 0, 4, 0, 0, 0, 0 }, // 2
                        { 8, 0, 0, 0, 0, 0, 0, 0, 7 },
                        { 0, 0, 3, 0, 6, 0, 5, 4, 0 },
                        { 0, 9, 0, 1, 0, 0, 8, 0, 0 },
                        { 0, 5, 0, 3, 0, 4, 0, 2, 0 },
                        { 0, 0, 7, 0, 0, 8, 0, 6, 0 },
                        { 0, 2, 4, 0, 0, 0, 0, 1, 0 },
                        { 0, 0, 1, 0, 0, 0, 0, 0, 3 },
                        { 0, 0, 0, 0, 2, 0, 0, 0, 0 } },

                {
                        { 0, 0, 0, 0, 0, 0, 0, 1, 0 }, // 3
                        { 0, 4, 2, 0, 0, 6, 7, 0, 0 },
                        { 0, 0, 0, 1, 0, 8, 0, 0, 0 },
                        { 0, 0, 5, 0, 0, 0, 4, 0, 6 },
                        { 6, 0, 0, 3, 0, 7, 0, 0, 9 },
                        { 2, 0, 9, 0, 0, 0, 1, 0, 7 },
                        { 0, 0, 0, 5, 0, 9, 0, 0, 0 },
                        { 0, 0, 8, 7, 0, 0, 5, 0, 0 },
                        { 0, 3, 0, 0, 4, 0, 0, 0, 0 } },

                {
                        { 0, 0, 4, 0, 6, 0, 5, 0, 0 }, // 4
                        { 3, 0, 0, 2, 0, 0, 0, 0, 7 },
                        { 6, 0, 0, 5, 0, 3, 8, 0, 0 },
                        { 9, 0, 8, 0, 0, 0, 3, 0, 0 },
                        { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                        { 0, 0, 7, 0, 0, 0, 4, 0, 1 },
                        { 0, 0, 3, 6, 0, 0, 0, 0, 9 },
                        { 8, 0, 0, 0, 0, 9, 0, 0, 4 },
                        { 0, 0, 6, 0, 8, 0, 2, 0, 0 } },

                {
                        { 0, 5, 7, 0, 4, 3, 0, 0, 0 }, // 5
                        { 8, 0, 9, 0, 0, 0, 0, 0, 0 },
                        { 0, 3, 0, 0, 0, 5, 0, 9, 0 },
                        { 0, 0, 2, 0, 7, 1, 0, 0, 0 },
                        { 0, 0, 4, 0, 0, 0, 2, 0, 0 },
                        { 0, 0, 8, 5, 0, 0, 9, 0, 0 },
                        { 0, 4, 0, 3, 0, 0, 0, 2, 0 },
                        { 0, 0, 0, 0, 0, 0, 4, 0, 1 },
                        { 0, 0, 0, 7, 2, 0, 3, 6, 0 } },

                {
                        { 0, 3, 0, 0, 0, 8, 0, 5, 0 }, // 6
                        { 0, 2, 0, 0, 9, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 7, 0, 0, 0, 4 },
                        { 1, 9, 0, 0, 0, 2, 0, 6, 7 },
                        { 2, 8, 0, 0, 0, 0, 0, 3, 5 },
                        { 6, 4, 0, 1, 0, 0, 0, 9, 8 },
                        { 5, 0, 0, 0, 3, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 6, 0, 0, 4, 0 },
                        { 0, 6, 0, 9, 0, 0, 0, 8, 0 } },

                {
                        { 0, 0, 0, 0, 2, 0, 1, 3, 0 }, // 7
                        { 0, 0, 0, 0, 7, 0, 0, 0, 4 },
                        { 0, 7, 3, 0, 0, 0, 6, 0, 2 },
                        { 0, 0, 9, 6, 8, 7, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 5, 4, 2, 7, 0, 0 },
                        { 8, 0, 5, 0, 0, 0, 2, 9, 0 },
                        { 3, 0, 0, 0, 5, 0, 0, 0, 0 },
                        { 9, 2, 4, 0, 1, 0, 0, 0, 0 } },

                {
                        { 7, 0, 4, 2, 0, 0, 3, 0, 8 }, // 8 very difficult
                        { 0, 2, 0, 0, 3, 0, 0, 9, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 9, 0, 0, 0, 0, 0, 6, 0, 0 },
                        { 0, 3, 0, 0, 6, 0, 0, 1, 0 },
                        { 0, 0, 8, 0, 0, 0, 0, 0, 7 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 6, 0, 0, 1, 0, 0, 4, 0 },
                        { 8, 0, 5, 0, 0, 6, 7, 0, 2 } },
                        
                
                        
                {
                        { 9, 0, 0, 0, 0, 0, 0, 0, 0 }, // 9 blank board
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 5, 0, 0, 0, 0, 0, 0, 0, 0 } }

        };

        System.out.println("Author: " + sudoku.myName());
        for (int i = 0; i < s.length; i++) {
            sudoku p = new sudoku(s[i]);
            if (p.isValid())
                System.out.println("Valid start board.");
            System.out.print(p);
            System.out.println();
            p.solveHard();
            if (p.isComplete())
                System.out.println("Solution found.");
            else
                System.out.println("Not done yet.");
            System.out.print(p);
            System.out.println();
        }
    }

}