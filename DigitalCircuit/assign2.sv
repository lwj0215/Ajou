module assign2(
    input logic clk,
    input logic reset,
    input logic d,
    output logic unlock
);
    logic state_0, state_1;
    logic next_0, next_1;

    // Next State Logic
    always_comb begin
        next_0 = ~(state_0 & state_1 & d) | (state_0 & state_1);
        next_1 = (state_0 | state_1) & d;
    end

    // State Register
    always_ff @(posedge clk, posedge reset) begin
        if (reset) next_1 <= 0; next_0 <= 0;
        state_0 <= next_0;
        state_1 <= next_1; 
    end

    // Output Logic
    assign unlock = state_0 & state_1;
endmodule