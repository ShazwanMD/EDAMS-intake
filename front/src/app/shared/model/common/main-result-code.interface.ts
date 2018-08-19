import { ResultType } from './../application/result-type.enum';
import { IntakeApplication } from './../application/intake-application.interface';
import { MetaObject } from './../../../core/meta-object.interface';

export interface MainResultCode extends MetaObject {
    aggregate: string;
    year: string;
    schoolName: string;
    application: IntakeApplication;
    resultType: ResultType;
  }