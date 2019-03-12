package io.openems.edge.common.type.slidingvalue;

import io.openems.common.types.OpenemsType;

public class DoubleSlidingValue extends AbstractNumberSlidingValue<Double> {

	@Override
	protected Double add(Double a, Double b) {
		return a + b;
	}

	@Override
	protected Double divide(Double a, int b) {
		return a / b;
	}

	protected OpenemsType getType() {
		return OpenemsType.DOUBLE;
	}

}
