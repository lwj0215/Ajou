`timescale 1ps/1ps

module assign2_tb();
    logic clk;
    logic reset;
    logic d;
    logic unlock;

    assign2 dut(clk, reset, d, unlock);

    // Generate clock
    always begin
        clk = 1; #5; 
        clk = 0; #5;
    end

    initial begin
        $dumpfile("assign2_tb.vcd");
        $dumpvars(0, assign2_tb);

        reset = 1; d=1; #5;
        reset = 0; #5;
        d = 0; #10;
        d = 1; #10;
        d = 1; #10;
        d = 1; #10;
        d = 0; #10;
        
        $display("Test Complete");
        #10;
        $finish(1);
    end
endmodule