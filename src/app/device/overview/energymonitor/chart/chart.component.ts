import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { BaseChartComponent, ColorHelper } from '@swimlane/ngx-charts';
import * as d3 from 'd3';
import { StorageSection, ProductionSection, ConsumptionSection, GridSection, AbstractSection } from './section/section';
import { Device } from '../../../../service/device';

class Circle {
  constructor(
    public x: number,
    public y: number
  ) { }
}

@Component({
  selector: 'app-device-overview-energymonitor-chart',
  templateUrl: './chart.component.html'
})
export class DeviceOverviewEnergymonitorChartComponent extends BaseChartComponent implements OnInit, OnChanges {

  private style: string;
  private translation: string;
  private gridSection: AbstractSection = new GridSection();
  private productionSection: AbstractSection = new ProductionSection();
  private consumptionSection: AbstractSection = new ConsumptionSection();
  private storageSection: AbstractSection = new StorageSection();
  private sections: AbstractSection[] = [this.gridSection, this.productionSection, this.consumptionSection, this.storageSection];

  @Input()
  set grid(value: number) {
    this.gridSection.setValue(value);
  }

  @Input()
  set production(value: number) {
    this.productionSection.setValue(value);
  }

  @Input()
  set consumption(value: number) {
    this.consumptionSection.setValue(value);
  }

  @Input()
  set storage(value: number) {
    this.storageSection.setValue(value);
  }

  ngOnInit() {
    this.update();
  }

  update() {
    super.update();
    console.log(this.height, this.width);
    this.height = this.width - 100;
    this.translation = `translate(${this.width / 2}, ${this.height / 2})`;
    var outerRadius = Math.min(this.width, this.height) / 2;
    var innerRadius = outerRadius - 30;
    this.sections.forEach(section => {
      section.update(outerRadius, innerRadius, this.height, this.width);
    });
  }

  private deg2rad(value: number): number {
    return value * (Math.PI / 180)
  }

  private circles: Circle[] = [];
  //   new Circle(-20, 0),
  //   new Circle(-50, 0),
  //   new Circle(-80, 0),
  //   new Circle(-110, 0),
  //   new Circle(20, 0),
  //   new Circle(50, 0),
  //   new Circle(80, 0),
  //   new Circle(110, 0),
  //   new Circle(0, -20),
  //   new Circle(0, -50),
  //   new Circle(0, -80),
  //   new Circle(0, -110),
  //   new Circle(0, 20),
  //   new Circle(0, 50),
  //   new Circle(0, 80),
  //   new Circle(0, 110)
  // ];
}
