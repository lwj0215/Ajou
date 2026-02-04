`timescale 1ps/1ps

module assign1_tb();
    logic clk;
    logic reset;
    logic d;
    logic alarm;

    assign1 dut(clk, reset, d, alarm);

    // Generate clock
    always begin
        clk = 1; #5; 
        clk = 0; #5;
    end

    initial begin
        $dumpfile("assign1_tb.vcd");
        $dumpvars(0, assign1_tb);

        reset = 1; d=0; #10;
        reset = 0; #10;
        d = 1; #10;
        d = 1; #10;
        d = 1; #10;
        d = 0; #10;
        
        $display("Test Complete");
        #10;
        $finish(1);
    end
endmodule