import {ProgramLevel} from "../../../policy/program-levels/program-level.interface";
export interface IntakeApplicationPersonal{

  id: number;
  startDate: Date;
  endDate: Date;
  employer:String;
  designation:String;

    // transient
  current?:boolean;
}
