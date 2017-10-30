package io.openems.impl.device.sma;

import io.openems.api.channel.ConfigChannel;
import io.openems.api.channel.ReadChannel;
import io.openems.api.channel.StaticValueChannel;
import io.openems.api.channel.StatusBitChannels;
import io.openems.api.channel.WriteChannel;
import io.openems.api.device.Device;
import io.openems.api.device.nature.ess.SymmetricEssNature;
import io.openems.api.exception.ConfigException;
import io.openems.impl.protocol.modbus.ModbusDeviceNature;
import io.openems.impl.protocol.modbus.ModbusReadLongChannel;
import io.openems.impl.protocol.modbus.ModbusWriteLongChannel;
import io.openems.impl.protocol.modbus.internal.DummyElement;
import io.openems.impl.protocol.modbus.internal.ModbusProtocol;
import io.openems.impl.protocol.modbus.internal.SignedDoublewordElement;
import io.openems.impl.protocol.modbus.internal.UnsignedDoublewordElement;
import io.openems.impl.protocol.modbus.internal.range.ModbusRegisterRange;
import io.openems.impl.protocol.modbus.internal.range.WriteableModbusRegisterRange;

public class SunnyIsland6Ess extends ModbusDeviceNature implements SymmetricEssNature{

	public SunnyIsland6Ess(String thingId, Device parent) throws ConfigException {
		super(thingId, parent);
	}

	/*
	 * Config
	 */
	private ConfigChannel<Integer> minSoc = new ConfigChannel<Integer>("minSoc", this);
	private ConfigChannel<Integer> chargeSoc = new ConfigChannel<Integer>("chargeSoc", this);
	private ConfigChannel<Long> capacity = new ConfigChannel<Long>("capacity", this).unit("Wh");

	@Override
	public ConfigChannel<Integer> minSoc() {
		return minSoc;
	}

	@Override
	public ConfigChannel<Integer> chargeSoc() {
		return chargeSoc;
	}

	private StatusBitChannels warning;
	private ModbusReadLongChannel allowedCharge;
	private ModbusReadLongChannel allowedDischarge;
	private ReadChannel<Long> gridMode= new StaticValueChannel<Long>("GridMode", this, 1L).label(1L, ON_GRID);
	private ModbusReadLongChannel soc;
	private ReadChannel<Long> systemState = new StaticValueChannel<Long>("SystemState", this, 1L).label(1L, START);
	private ModbusReadLongChannel activePower;
	private ModbusReadLongChannel reactivePower;
	private ModbusWriteLongChannel setActivePower;
	private ModbusWriteLongChannel setReactivePower;
	private ModbusWriteLongChannel setControlMode;
	private ReadChannel<Long> apparentPower;
	private ReadChannel<Long> allowedApparent = new StaticValueChannel<Long>("AllowedApparent", this, 6000L);
	private StaticValueChannel<Long> nominalPower = new StaticValueChannel<Long>("maxNominalPower", this, 6000l)
			.unit("VA");
	public ModbusReadLongChannel frequency;
	public ModbusReadLongChannel current;
	public ModbusReadLongChannel voltage;
	public ModbusReadLongChannel batteryTemperature;
	public ModbusReadLongChannel batteryCurrent;
	public ModbusReadLongChannel batteryVoltage;
	public ModbusReadLongChannel allowedChargeCurrent;
	public ModbusReadLongChannel allowedDischargeCurrent;

	@Override
	public ReadChannel<Long> gridMode() {
		return gridMode;
	}

	@Override
	public ReadChannel<Long> soc() {
		return soc;
	}

	@Override
	public ReadChannel<Long> systemState() {
		return systemState;
	}

	@Override
	public ReadChannel<Long> allowedCharge() {
		return allowedCharge;
	}

	@Override
	public ReadChannel<Long> allowedDischarge() {
		return allowedDischarge;
	}

	@Override
	public ReadChannel<Long> allowedApparent() {
		return allowedApparent;
	}

	@Override
	public ReadChannel<Long> capacity() {
		return capacity;
	}

	@Override
	public ReadChannel<Long> maxNominalPower() {
		return nominalPower;
	}

	@Override
	public StatusBitChannels warning() {
		return warning;
	}

	@Override
	public WriteChannel<Long> setWorkState() {
		return setControlMode;
	}

	@Override
	public ReadChannel<Long> activePower() {
		return activePower;
	}

	@Override
	public ReadChannel<Long> apparentPower() {
		return apparentPower;
	}

	@Override
	public ReadChannel<Long> reactivePower() {
		return reactivePower;
	}

	@Override
	public WriteChannel<Long> setActivePower() {
		return setActivePower;
	}

	@Override
	public WriteChannel<Long> setReactivePower() {
		return setReactivePower;
	}

	@Override
	protected ModbusProtocol defineModbusProtocol() throws ConfigException {
		warning = new StatusBitChannels("Warning", this);

		ModbusProtocol protokol = new ModbusProtocol(
				new ModbusRegisterRange(30775, //
						new SignedDoublewordElement(30775, //
								activePower = new ModbusReadLongChannel("ActivePower", this).unit("W")),
						new DummyElement(30777,30802),
						new UnsignedDoublewordElement(30803, //
								frequency = new ModbusReadLongChannel("Frequency", this).unit("mHz").multiplier(1)),
						new SignedDoublewordElement(30805, //
								reactivePower = new ModbusReadLongChannel("ReactivePower", this).unit("var").negate())
						),
				new ModbusRegisterRange(30843, //
						new SignedDoublewordElement(30843, //
								batteryCurrent = new ModbusReadLongChannel("BatteryCurrent", this).unit("mA")),
						new UnsignedDoublewordElement(30845, //
								soc = new ModbusReadLongChannel("Soc", this).unit("%").interval(0, 100)),
						new DummyElement(30847,30848),
						new SignedDoublewordElement(30849, //
								batteryTemperature = new ModbusReadLongChannel("BatteryTemperature", this).unit("°C").multiplier(-1)),
						new UnsignedDoublewordElement(30851, //
								batteryVoltage = new ModbusReadLongChannel("BatteryVoltage", this).unit("mV").multiplier(1))),
				new ModbusRegisterRange(40189,
						new UnsignedDoublewordElement(40189, //
								allowedCharge = new ModbusReadLongChannel("AllowedCharge", this).unit("W").negate()),
						new UnsignedDoublewordElement(40191, //
								allowedDischarge = new ModbusReadLongChannel("AllowedDischarge", this).unit("W"))
						),
				new WriteableModbusRegisterRange(40149, //
						new SignedDoublewordElement(40149,
								setActivePower = new ModbusWriteLongChannel("SetActivePower", this).unit("W")), //
						new UnsignedDoublewordElement(40151,
								setControlMode = new ModbusWriteLongChannel("SetControlMode", this).label(802, START).label(803, STOP)), //
						new SignedDoublewordElement(40153,
								setReactivePower = new ModbusWriteLongChannel("SetReactivePower", this).unit("Var"))));
		//		allowedCharge = new FunctionalReadChannel<Long>("AllowedCharge", this, new FunctionalReadChannelFunction<Long>() {
		//
		//			@Override
		//			public Long handle(ReadChannel<Long>... channels) throws InvalidValueException {
		//				if(channels.length == 2 && channels[0].valueOptional().isPresent() && channels[1].valueOptional().isPresent()) {
		//					return (channels[0].valueOptional().get()/1000)*(channels[1].valueOptional().get()/1000)*-1;
		//				}
		//				return null;
		//			}
		//
		//		},batteryVoltage,allowedChargeCurrent);
		//		allowedDischarge = new FunctionalReadChannel<Long>("AllowedCharge", this, new FunctionalReadChannelFunction<Long>() {
		//
		//			@Override
		//			public Long handle(ReadChannel<Long>... channels) throws InvalidValueException {
		//				if(channels.length == 2 && channels[0].valueOptional().isPresent() && channels[1].valueOptional().isPresent()) {
		//					return (channels[0].valueOptional().get()/1000)*(channels[1].valueOptional().get()/1000);
		//				}
		//				return null;
		//			}
		//
		//		},batteryVoltage,allowedDischargeCurrent);
		return protokol;
	}

}
