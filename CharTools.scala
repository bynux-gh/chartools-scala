// Program to encode/decode hexadecimal and ASCII/Unicode :]

/** Main Program */
@main def charTools(args: String*) : Unit = {
    var opt = 0x00.asInstanceOf[Char]
    var exitCode = 0; // Reset if issue arises.
    val useInteractive = (args.length < 1); // If run with interactive prompt.
    
    if (useInteractive) {
        print("(E)ncode, (D)ecode, (T)able, or (Q)uit?\n> ");
        opt = Console.in.readLine().charAt(0); // Prompt to select function
    } else {
        opt = args(0).charAt(0);
    }

    opt match {
        
        case 'e' | 'E' => {  // Encode to HEX
            var msg: String = "";
            if (args.length > 1) {
                msg = args(1);
            } else {
                print("Enter string to encode.\n> ");
                msg = Console.in.readLine();
            }
            println(encodeHex(msg) + "\n");
        }

        case 'd' | 'D' => {
            var msg: String = "";
            if (args.length > 1) {
                msg = args(1);
            } else {
                println("Enter HEX string to decode."); // Decode from HEX
                print("Separate values with commas and/or spaces.\n> ");
                msg = Console.in.readLine();
            }

            try {
                println(decodeHex(msg) + "\n");
            } catch {
                case e : NumberFormatException => { // If invalid string...
                    println("Please enter valid hexadecimal, and use " +
                            "only commas and spaces " +
                            "to separate each value.\n"); // ...give [warn].
                    exitCode = 2;
                }
                case x : Exception => {
                    System.err.println(x.getMessage); // If there's some other [error],
                    exitCode = 255; // print message and quit w/ error code 1.
                }
            }
        }

        case 'f' | 'F' => { // Press F to pay respects.
            println("Respects have been paid.\n")
        }

        case 't' | 'T' => { println(charTable) } // Print ASCII table.

        case 'q' | 'Q' => { // Quit program immediately.
            System.exit(0);
        }

        case _ => {  // If other selection is made,
            println("Please enter a valid command.\n");  // give [error] message.
            exitCode = 1;
        }
    }

    if (useInteractive) {
        charTools();
    } else {
        System.exit(exitCode);
    }

}

/**
* Decodes a string of hexadecimal numbers into a string of characters.
*
* @param input A string with only valid HEX numbers separated by spaces and/or commas.
*/
def decodeHex(input: String): String = {
    input
        .split("(\\s|,)+") // Split into individual values
        .map(Integer.parseInt(_, 16)) // Parse as hex into numbers
        .map(_.toChar) // Convert numbers to ASCII
        .mkString; // Merge results into a string
}

/**
* Converts a regular string into the equivalent hexadecimal values.
*
* @param input Any string of valid ASCII/Unicode characters.
*/
def encodeHex(input: String): String = {
    input
        .map(_.toInt.toHexString) // Convert to number, then hex
        .mkString(" "); // Merge with space separators
}

val charTable = """
 ASCII | HEX | DEC     ASCII | HEX | DEC     ASCII | HEX | DEC 
-------|-----|-----   -------|-----|-----   -------|-----|-----
       |  20 |  32         @ |  40 |  64         ` |  60 |  96 
     ! |  21 |  33         A |  41 |  65         a |  61 |  97 
     " |  22 |  34         B |  42 |  66         b |  62 |  98 
     # |  23 |  35         C |  43 |  67         c |  63 |  99 
     $ |  24 |  36         D |  44 |  68         d |  64 | 100 
     % |  25 |  37         E |  45 |  69         e |  65 | 101 
     & |  26 |  38         F |  46 |  70         f |  66 | 102 
     ' |  27 |  39         G |  47 |  71         g |  67 | 103 
     ( |  28 |  40         H |  48 |  72         h |  68 | 104 
     ) |  29 |  41         I |  49 |  73         i |  69 | 105 
     * |  2a |  42         J |  4a |  74         j |  6a | 106 
     + |  2b |  43         K |  4b |  75         k |  6b | 107 
     , |  2c |  44         L |  4c |  76         l |  6c | 108 
     - |  2d |  45         M |  4d |  77         m |  6d | 109 
     . |  2e |  46         N |  4e |  78         n |  6e | 110 
     / |  2f |  47         O |  4f |  79         o |  6f | 111 
     0 |  30 |  48         P |  50 |  80         p |  70 | 112 
     1 |  31 |  49         Q |  51 |  81         q |  71 | 113 
     2 |  32 |  50         R |  52 |  82         r |  72 | 114 
     3 |  33 |  51         S |  53 |  83         s |  73 | 115 
     4 |  34 |  52         T |  54 |  84         t |  74 | 116 
     5 |  35 |  53         U |  55 |  85         u |  75 | 117 
     6 |  36 |  54         V |  56 |  86         v |  76 | 118 
     7 |  37 |  55         W |  57 |  87         w |  77 | 119 
     8 |  38 |  56         X |  58 |  88         x |  78 | 120 
     9 |  39 |  57         Y |  59 |  89         y |  79 | 121 
     : |  3a |  58         Z |  5a |  90         z |  7a | 122 
     ; |  3b |  59         [ |  5b |  91         { |  7b | 123 
     < |  3c |  60         \ |  5c |  92         | |  7c | 124 
     = |  3d |  61         ] |  5d |  93         } |  7d | 125 
     > |  3e |  62         ^ |  5e |  94         ~ |  7e | 126 
     ? |  3f |  63         _ |  5f |  95      BKSP |  7f | 127
"""
