// `timescale 1ps/1ps
module eg_tb();
    // timeunit 1ns;
    // timeprecision 1ps;

    logic a, b, c, y;
    // instantiate device under test
    example dut(a, b, c, y);
    // apply inputs one at a time
    initial begin
        $dumpfile("eg_tb.vcd");
        $dumpvars(0,eg_tb);
        a = 0; b = 0; c = 0; #1;
    end
endmodule