import { MetaObject } from "../../../core/meta-object.interface";
import { GradeCode } from "./grade-code.interface";
import { StpmSubjectCode } from "./stpm-subject-code.interface";
import { IntakeApplication } from "../application/intake-application.interface";
import { ResultType } from "../application/result-type.enum";


export interface StpmResultCode extends MetaObject {
    aggregate: string;
    graduationYear: string;
    gradeCode: GradeCode;
    stpmSubjectCode: StpmSubjectCode;
    application: IntakeApplication;
    resultType: ResultType;
  }