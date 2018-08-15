import {MetaObject} from "../../../core/meta-object.interface";
import { SubjectCode } from "./subject-code.interface";
export interface SpmSubjectCode extends MetaObject{
  code:string;
  description:string;

}
