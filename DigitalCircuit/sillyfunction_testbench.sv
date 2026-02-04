module testbench1();
  logic  a, b, c, y;

  // instantiate device under test
  sillyfunction dut(a, b, c, y);

  // apply inputs one at a time
  initial begin
    $dumpfile("silly_tb.vcd"); //gtkwave silly_tb.vcd
    $dumpvars(0, testbench1); // 0 is level of layer
    a = 0; b = 0; c = 0; #10; //#10 is delay 10-unittime
    c = 1;               #10;
    b = 1; c = 0;        #10;
    c = 1;               #10;
    a = 1; b = 0; c = 0; #10;
    c = 1;               #10;
    b = 1; c = 0;        #10;
    c = 1;               #10;
  end
endmodule
