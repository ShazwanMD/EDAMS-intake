import {MetaObject} from "../../../core/meta-object.interface";
import {ProgramFieldCode} from "../common/program-field-code.interface";
import { Intake } from "./intake.interface";
import { ProgramCode } from "../common/program-code.interface";
export interface ProgramOffering extends MetaObject {
  id:number;
  projection:number;
  interview:boolean;
  generalCriteria:string;
  specificCriteria:string;
  programCode:ProgramCode;
  intake: Intake;
}
