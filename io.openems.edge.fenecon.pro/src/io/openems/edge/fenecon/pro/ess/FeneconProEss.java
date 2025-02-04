package io.openems.edge.fenecon.pro.ess;

import io.openems.common.channel.AccessMode;
import io.openems.common.channel.Level;
import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.sum.GridMode;
import io.openems.edge.ess.api.SymmetricEss;

public interface FeneconProEss extends SymmetricEss, OpenemsComponent {

	public enum ChannelId implements io.openems.edge.common.channel.ChannelId {
		// EnumReadChannels
		WORK_MODE(Doc.of(WorkMode.values())), //
		SYSTEM_STATE(Doc.of(SystemState.values()) //
				.<FeneconProEss>onChannelChange((self, value) -> {
					// on each change set Grid-Mode channel
					SystemState systemState = value.asEnum();
					self._setGridMode(switch (systemState) {
					case STANDBY, START, FAULT -> GridMode.ON_GRID;
					case START_OFF_GRID, OFF_GRID_PV -> GridMode.OFF_GRID;
					case UNDEFINED -> GridMode.UNDEFINED;
					});
				})), //

		CONTROL_MODE(Doc.of(ControlMode.values())), //
		BATTERY_GROUP_STATE(Doc.of(BatteryGroupState.values())), //
		PCS_OPERATION_STATE(Doc.of(PcsOperationState.values())), //

		// EnumWriteChannels
		SET_WORK_STATE(Doc.of(SetWorkState.values()) //
				.accessMode(AccessMode.WRITE_ONLY)), //
		SETUP_MODE(Doc.of(SetupMode.values()) //
				.accessMode(AccessMode.READ_WRITE)), //
		PCS_MODE(Doc.of(PcsMode.values()) //
				.accessMode(AccessMode.READ_WRITE)), //

		// IntegerWriteChannels
		SET_ACTIVE_POWER_L1(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.unit(Unit.WATT)), //
		SET_ACTIVE_POWER_L2(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.unit(Unit.WATT)), //
		SET_ACTIVE_POWER_L3(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.unit(Unit.WATT)), //
		SET_REACTIVE_POWER_L1(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.unit(Unit.VOLT_AMPERE)), //
		SET_REACTIVE_POWER_L2(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.unit(Unit.VOLT_AMPERE)), //
		SET_REACTIVE_POWER_L3(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.unit(Unit.VOLT_AMPERE)), //
		RTC_YEAR(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.text("Year")), //
		RTC_MONTH(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.text("Month")), //
		RTC_DAY(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.text("Day")), //
		RTC_HOUR(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.text("Hour")), //
		RTC_MINUTE(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.text("Minute")), //
		RTC_SECOND(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.WRITE_ONLY) //
				.text("Second")), //

		// IntegerReadChannels
		TOTAL_BATTERY_CHARGE_ENERGY(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.WATT_HOURS)), //
		TOTAL_BATTERY_DISCHARGE_ENERGY(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.WATT_HOURS)), //
		BATTERY_POWER(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.WATT)), //
		BATTERY_VOLTAGE(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_CURRENT(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIAMPERE)), //
		CURRENT_L1(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIAMPERE)), //
		CURRENT_L2(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIAMPERE)), //
		CURRENT_L3(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIAMPERE)), //
		VOLTAGE_L1(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		VOLTAGE_L2(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		VOLTAGE_L3(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		FREQUENCY_L1(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIHERTZ)), //
		FREQUENCY_L2(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIHERTZ)), //
		FREQUENCY_L3(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIHERTZ)), //
		SINGLE_PHASE_ALLOWED_APPARENT(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.VOLT_AMPERE)), //
		BATTERY_VOLTAGE_SECTION_1(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_2(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_3(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_4(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_5(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_6(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_7(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_8(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_9(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_10(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_11(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_12(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_13(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_14(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_15(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		BATTERY_VOLTAGE_SECTION_16(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.MILLIVOLT)), //
		// TODO add .delta(-40L)
		BATTERY_TEMPERATURE_SECTION_1(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_2(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_3(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_4(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_5(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_6(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_7(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_8(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_9(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_10(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_11(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_12(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_13(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_14(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_15(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //
		BATTERY_TEMPERATURE_SECTION_16(Doc.of(OpenemsType.INTEGER) //
				.unit(Unit.DEGREE_CELSIUS)), //

		// StateChannels
		STATE_0(Doc.of(Level.INFO) //
				.text("FailTheSystemShouldBeStopped")), //
		STATE_1(Doc.of(Level.INFO) //
				.text("CommonLowVoltageAlarm")), //
		STATE_2(Doc.of(Level.INFO) //
				.text("CommonHighVoltageAlarm")), //
		STATE_3(Doc.of(Level.INFO) //
				.text("ChargingOverCurrentAlarm")), //
		STATE_4(Doc.of(Level.INFO) //
				.text("DischargingOverCurrentAlarm")), //
		STATE_5(Doc.of(Level.INFO) //
				.text("OverTemperatureAlarm")), //
		STATE_6(Doc.of(Level.INFO) //
				.text("InteralCommunicationAbnormal")), //
		STATE_7(Doc.of(Level.INFO) //
				.text("GridUndervoltageL1")), //
		STATE_8(Doc.of(Level.INFO) //
				.text("GridOvervoltageL1")), //
		STATE_9(Doc.of(Level.INFO) //
				.text("GridUnderFrequencyL1")), //
		STATE_10(Doc.of(Level.INFO) //
				.text("GridOverFrequencyL1")), //
		STATE_11(Doc.of(Level.INFO) //
				.text("GridPowerSupplyOffL1")), //
		STATE_12(Doc.of(Level.INFO) //
				.text("GridConditionUnmeetL1")), //
		STATE_14(Doc.of(Level.INFO) //
				.text("InputOverResistanceL1")), //
		STATE_15(Doc.of(Level.INFO) //
				.text("CombinationErrorL1")), //
		STATE_17(Doc.of(Level.INFO) //
				.text("TmeErrorL1")), //
		STATE_18(Doc.of(Level.INFO) //
				.text("PcsAlarm2L1")), //
		STATE_19(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload100PercentL1")), //
		STATE_20(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload110PercentL1")), //
		STATE_21(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload150PercentL1")), //
		STATE_22(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload200PercentL1")), //
		STATE_23(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload210PercentL1")), //
		STATE_24(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload300PercentL1")), //
		STATE_25(Doc.of(Level.INFO) //
				.text("ControlTransientLoad300PercentL1")), //
		STATE_26(Doc.of(Level.INFO) //
				.text("GridOverCurrentL1")), //
		STATE_27(Doc.of(Level.INFO) //
				.text("LockingWaveformTooManyTimesL1")), //
		STATE_28(Doc.of(Level.INFO) //
				.text("InverterVoltageZeroDriftErrorL1")), //
		STATE_29(Doc.of(Level.INFO) //
				.text("GridVoltageZeroDriftErrorL1")), //
		STATE_30(Doc.of(Level.INFO) //
				.text("ControlCurrentZeroDriftErrorL1")), //
		STATE_31(Doc.of(Level.INFO) //
				.text("InverterCurrentZeroDriftErrorL1")), //
		STATE_32(Doc.of(Level.INFO) //
				.text("GridCurrentZeroDriftErrorL1")), //
		STATE_33(Doc.of(Level.INFO) //
				.text("PDPProtectionL1")), //
		STATE_34(Doc.of(Level.INFO) //
				.text("HardwareControlCurrentProtectionL1")), //
		STATE_35(Doc.of(Level.INFO) //
				.text("HardwareACVoltageProtectionL1")), //
		STATE_36(Doc.of(Level.INFO) //
				.text("HardwareDCCurrentProtectionL1")), //
		STATE_37(Doc.of(Level.INFO) //
				.text("HardwareTemperatureProtectionL1")), //
		STATE_38(Doc.of(Level.INFO) //
				.text("NoCapturingSignalL1")), //
		STATE_39(Doc.of(Level.INFO) //
				.text("DCOvervoltageL1")), //
		STATE_41(Doc.of(Level.INFO) //
				.text("InverterUndervoltageL1")), //
		STATE_42(Doc.of(Level.INFO) //
				.text("InverterOvervoltageL1")), //
		STATE_43(Doc.of(Level.INFO) //
				.text("CurrentSensorFailL1")), //
		STATE_44(Doc.of(Level.INFO) //
				.text("VoltageSensorFailL1")), //
		STATE_45(Doc.of(Level.INFO) //
				.text("PowerUncontrollableL1")), //
		STATE_46(Doc.of(Level.INFO) //
				.text("CurrentUncontrollableL1")), //
		STATE_47(Doc.of(Level.INFO) //
				.text("FanErrorL1")), //
		STATE_48(Doc.of(Level.INFO) //
				.text("PhaseLackL1")), //
		STATE_49(Doc.of(Level.INFO) //
				.text("InverterRelayFaultL1")), //
		STATE_50(Doc.of(Level.INFO) //
				.text("GridRelayFaultL1")), //
		STATE_51(Doc.of(Level.INFO) //
				.text("ControlPanelOvertempL1")), //
		STATE_52(Doc.of(Level.INFO) //
				.text("PowerPanelOvertempL1")), //
		STATE_53(Doc.of(Level.INFO) //
				.text("DCInputOvercurrentL1")), //
		STATE_54(Doc.of(Level.INFO) //
				.text("CapacitorOvertempL1")), //
		STATE_55(Doc.of(Level.INFO) //
				.text("RadiatorOvertempL1")), //
		STATE_56(Doc.of(Level.INFO) //
				.text("TransformerOvertempL1")), //
		STATE_57(Doc.of(Level.INFO) //
				.text("CombinationCommErrorL1")), //
		STATE_58(Doc.of(Level.INFO) //
				.text("EEPROMErrorL1")), //
		STATE_59(Doc.of(Level.INFO) //
				.text("LoadCurrentZeroDriftErrorL1")), //
		STATE_60(Doc.of(Level.INFO) //
				.text("CurrentLimitRErrorL1")), //
		STATE_61(Doc.of(Level.INFO) //
				.text("PhaseSyncErrorL1")), //
		STATE_62(Doc.of(Level.INFO) //
				.text("ExternalPVCurrentZeroDriftErrorL1")), //
		STATE_63(Doc.of(Level.INFO) //
				.text("ExternalGridCurrentZeroDriftErrorL1")), //
		STATE_64(Doc.of(Level.INFO) //
				.text("GridUndervoltageL2")), //
		STATE_65(Doc.of(Level.INFO) //
				.text("GridOvervoltageL2")), //
		STATE_66(Doc.of(Level.INFO) //
				.text("GridUnderFrequencyL2")), //
		STATE_67(Doc.of(Level.INFO) //
				.text("GridOverFrequencyL2")), //
		STATE_68(Doc.of(Level.INFO) //
				.text("GridPowerSupplyOffL2")), //
		STATE_69(Doc.of(Level.INFO) //
				.text("GridConditionUnmeetL2")), //
		STATE_71(Doc.of(Level.INFO) //
				.text("InputOverResistanceL2")), //
		STATE_72(Doc.of(Level.INFO) //
				.text("CombinationErrorL2")), //
		STATE_74(Doc.of(Level.INFO) //
				.text("TmeErrorL2")), //
		STATE_75(Doc.of(Level.INFO) //
				.text("PcsAlarm2L2")), //
		STATE_76(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload100PercentL2")), //
		STATE_77(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload110PercentL2")), //
		STATE_78(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload150PercentL2")), //
		STATE_79(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload200PercentL2")), //
		STATE_80(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload210PercentL2")), //
		STATE_81(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload300PercentL2")), //
		STATE_82(Doc.of(Level.INFO) //
				.text("ControlTransientLoad300PercentL2")), //
		STATE_83(Doc.of(Level.INFO) //
				.text("GridOverCurrentL2")), //
		STATE_84(Doc.of(Level.INFO) //
				.text("LockingWaveformTooManyTimesL2")), //
		STATE_85(Doc.of(Level.INFO) //
				.text("InverterVoltageZeroDriftErrorL2")), //
		STATE_86(Doc.of(Level.INFO) //
				.text("GridVoltageZeroDriftErrorL2")), //
		STATE_87(Doc.of(Level.INFO) //
				.text("ControlCurrentZeroDriftErrorL2")), //
		STATE_88(Doc.of(Level.INFO) //
				.text("InverterCurrentZeroDriftErrorL2")), //
		STATE_89(Doc.of(Level.INFO) //
				.text("GridCurrentZeroDriftErrorL2")), //
		STATE_90(Doc.of(Level.INFO) //
				.text("PDPProtectionL2")), //
		STATE_91(Doc.of(Level.INFO) //
				.text("HardwareControlCurrentProtectionL2")), //
		STATE_92(Doc.of(Level.INFO) //
				.text("HardwareACVoltageProtectionL2")), //
		STATE_93(Doc.of(Level.INFO) //
				.text("HardwareDCCurrentProtectionL2")), //
		STATE_94(Doc.of(Level.INFO) //
				.text("HardwareTemperatureProtectionL2")), //
		STATE_95(Doc.of(Level.INFO) //
				.text("NoCapturingSignalL2")), //
		STATE_96(Doc.of(Level.INFO) //
				.text("DCOvervoltageL2")), //
		STATE_98(Doc.of(Level.INFO) //
				.text("InverterUndervoltageL2")), //
		STATE_99(Doc.of(Level.INFO) //
				.text("InverterOvervoltageL2")), //
		STATE_100(Doc.of(Level.INFO) //
				.text("CurrentSensorFailL2")), //
		STATE_101(Doc.of(Level.INFO) //
				.text("VoltageSensorFailL2")), //
		STATE_102(Doc.of(Level.INFO) //
				.text("PowerUncontrollableL2")), //
		STATE_103(Doc.of(Level.INFO) //
				.text("CurrentUncontrollableL2")), //
		STATE_104(Doc.of(Level.INFO) //
				.text("FanErrorL2")), //
		STATE_105(Doc.of(Level.INFO) //
				.text("PhaseLackL2")), //
		STATE_106(Doc.of(Level.INFO) //
				.text("InverterRelayFaultL2")), //
		STATE_107(Doc.of(Level.INFO) //
				.text("GridRelayFaultL2")), //
		STATE_108(Doc.of(Level.INFO) //
				.text("ControlPanelOvertempL2")), //
		STATE_109(Doc.of(Level.INFO) //
				.text("PowerPanelOvertempL2")), //
		STATE_110(Doc.of(Level.INFO) //
				.text("DCInputOvercurrentL2")), //
		STATE_111(Doc.of(Level.INFO) //
				.text("CapacitorOvertempL2")), //
		STATE_112(Doc.of(Level.INFO) //
				.text("RadiatorOvertempL2")), //
		STATE_113(Doc.of(Level.INFO) //
				.text("TransformerOvertempL2")), //
		STATE_114(Doc.of(Level.INFO) //
				.text("CombinationCommErrorL2")), //
		STATE_115(Doc.of(Level.INFO) //
				.text("EEPROMErrorL2")), //
		STATE_116(Doc.of(Level.INFO) //
				.text("LoadCurrentZeroDriftErrorL2")), //
		STATE_117(Doc.of(Level.INFO) //
				.text("CurrentLimitRErrorL2")), //
		STATE_118(Doc.of(Level.INFO) //
				.text("PhaseSyncErrorL2")), //
		STATE_119(Doc.of(Level.INFO) //
				.text("ExternalPVCurrentZeroDriftErrorL2")), //
		STATE_120(Doc.of(Level.INFO) //
				.text("ExternalGridCurrentZeroDriftErrorL2")), //
		STATE_121(Doc.of(Level.INFO) //
				.text("GridUndervoltageL3")), //
		STATE_122(Doc.of(Level.INFO) //
				.text("GridOvervoltageL3")), //
		STATE_123(Doc.of(Level.INFO) //
				.text("GridUnderFrequencyL3")), //
		STATE_124(Doc.of(Level.INFO) //
				.text("GridOverFrequencyL3")), //
		STATE_125(Doc.of(Level.INFO) //
				.text("GridPowerSupplyOffL3")), //
		STATE_126(Doc.of(Level.INFO) //
				.text("GridConditionUnmeetL3")), //
		STATE_128(Doc.of(Level.INFO) //
				.text("InputOverResistanceL3")), //
		STATE_129(Doc.of(Level.INFO) //
				.text("CombinationErrorL3")), //
		STATE_131(Doc.of(Level.INFO) //
				.text("TmeErrorL3")), //
		STATE_132(Doc.of(Level.INFO) //
				.text("PcsAlarm2L3")), //
		STATE_133(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload100PercentL3")), //
		STATE_134(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload110PercentL3")), //
		STATE_135(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload150PercentL3")), //
		STATE_136(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload200PercentL3")), //
		STATE_137(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload210PercentL3")), //
		STATE_138(Doc.of(Level.INFO) //
				.text("ControlCurrentOverload300PercentL3")), //
		STATE_139(Doc.of(Level.INFO) //
				.text("ControlTransientLoad300PercentL3")), //
		STATE_140(Doc.of(Level.INFO) //
				.text("GridOverCurrentL3")), //
		STATE_141(Doc.of(Level.INFO) //
				.text("LockingWaveformTooManyTimesL3")), //
		STATE_142(Doc.of(Level.INFO) //
				.text("InverterVoltageZeroDriftErrorL3")), //
		STATE_143(Doc.of(Level.INFO) //
				.text("GridVoltageZeroDriftErrorL3")), //
		STATE_144(Doc.of(Level.INFO) //
				.text("ControlCurrentZeroDriftErrorL3")), //
		STATE_145(Doc.of(Level.INFO) //
				.text("InverterCurrentZeroDriftErrorL3")), //
		STATE_146(Doc.of(Level.INFO) //
				.text("GridCurrentZeroDriftErrorL3")), //
		STATE_147(Doc.of(Level.INFO) //
				.text("PDPProtectionL3")), //
		STATE_148(Doc.of(Level.INFO) //
				.text("HardwareControlCurrentProtectionL3")), //
		STATE_149(Doc.of(Level.INFO) //
				.text("HardwareACVoltageProtectionL3")), //
		STATE_150(Doc.of(Level.INFO) //
				.text("HardwareDCCurrentProtectionL3")), //
		STATE_151(Doc.of(Level.INFO) //
				.text("HardwareTemperatureProtectionL3")), //
		STATE_152(Doc.of(Level.INFO) //
				.text("NoCapturingSignalL3")), //
		STATE_153(Doc.of(Level.INFO) //
				.text("DCOvervoltageL3")), //
		STATE_155(Doc.of(Level.INFO) //
				.text("InverterUndervoltageL3")), //
		STATE_156(Doc.of(Level.INFO) //
				.text("InverterOvervoltageL3")), //
		STATE_157(Doc.of(Level.INFO) //
				.text("CurrentSensorFailL3")), //
		STATE_158(Doc.of(Level.INFO) //
				.text("VoltageSensorFailL3")), //
		STATE_159(Doc.of(Level.INFO) //
				.text("PowerUncontrollableL3")), //
		STATE_160(Doc.of(Level.INFO) //
				.text("CurrentUncontrollableL3")), //
		STATE_161(Doc.of(Level.INFO) //
				.text("FanErrorL3")), //
		STATE_162(Doc.of(Level.INFO) //
				.text("PhaseLackL3")), //
		STATE_163(Doc.of(Level.INFO) //
				.text("InverterRelayFaultL3")), //
		STATE_164(Doc.of(Level.INFO) //
				.text("GridRelayFaultL3")), //
		STATE_165(Doc.of(Level.INFO) //
				.text("ControlPanelOvertempL3")), //
		STATE_166(Doc.of(Level.INFO) //
				.text("PowerPanelOvertempL3")), //
		STATE_167(Doc.of(Level.INFO) //
				.text("DCInputOvercurrentL3")), //
		STATE_168(Doc.of(Level.INFO) //
				.text("CapacitorOvertempL3")), //
		STATE_169(Doc.of(Level.INFO) //
				.text("RadiatorOvertempL3")), //
		STATE_170(Doc.of(Level.INFO) //
				.text("TransformerOvertempL3")), //
		STATE_171(Doc.of(Level.INFO) //
				.text("CombinationCommErrorL3")), //
		STATE_172(Doc.of(Level.INFO) //
				.text("EEPROMErrorL3")), //
		STATE_173(Doc.of(Level.INFO) //
				.text("LoadCurrentZeroDriftErrorL3")), //
		STATE_174(Doc.of(Level.INFO) //
				.text("CurrentLimitRErrorL3")), //
		STATE_175(Doc.of(Level.INFO) //
				.text("PhaseSyncErrorL3")), //
		STATE_176(Doc.of(Level.INFO) //
				.text("ExternalPVCurrentZeroDriftErrorL3")), //
		STATE_177(Doc.of(Level.INFO) //
				.text("ExternalGridCurrentZeroDriftErrorL3")), //
		LOCAL_MODE(Doc.of(Level.WARNING) //
				.text("System is in Local-Mode, Remote-Mode is required!"));

		private final Doc doc;

		private ChannelId(Doc doc) {
			this.doc = doc;
		}

		@Override
		public Doc doc() {
			return this.doc;
		}
	}

}
