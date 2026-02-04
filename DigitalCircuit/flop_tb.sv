`timescale 1ps/1ps // set time unit

module flop_tb();
    logic clk;
    logic d; 
    logic q;

    flop dut(clk, d, q);

    // generate clock
    always begin // always = excute every time unit
        clk = 1; #1; 
        clk = 0; #1;
    end

    // always begin 
    //     clk = 1; #1;
    //     clk = ~clk; #1;
    // end

    initial begin
        $dumpfile("flop_tb.vcd");
        $dumpvars(0, flop_tb);

        d = 0; #3;
        d = 1; #3;
        d = 0; #3;
        d = 1; #3;
        d = 0; #3;
        d = 1; #3;
        d = 0; #3;
        d = 1; #3;

        $display("Test Complete");
        #10;
        $finish(1); // finish always block, 0/1/2
    end

endmodule