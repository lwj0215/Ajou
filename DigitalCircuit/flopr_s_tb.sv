`timescale 1ps/1ps

module flop_s_tb();
    logic clk;
    logic reset;
    logic [3:0] d; 
    logic [3:0] q;

    flopr_s dut(clk, reset, d, q);

    // generate clock
    always begin
        clk = 1; #1; 
        clk = 0; #1;
    end

    // always begin 
    //     clk = 1; #1;
    //     clk = ~clk; #1;
    // end

    initial begin
        $dumpfile("flop_s_tb.vcd");
        $dumpvars(0, flop_s_tb);

        d = 4'b0000; #3;
        d = 4'b1111; #3;
        reset = 1; #1;
        d = 4'b0000; #3;
        d = 4'b1111; #3;
        reset = 1; #1;
        d = 4'b0000; #3;
        d = 4'b1111; #3;
        d = 4'b0000; #3;
        d = 4'b1111; #3;

        $display("Test Complete");
        #10;
        $finish(1);
    end

endmodule