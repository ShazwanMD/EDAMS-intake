import { MetaObject } from './../../../core/meta-object.interface';
import { IntakeApplication } from './intake-application.interface';
import { ResultType } from './result-type.enum';


export interface DiplomaResultCode extends MetaObject{

    cgpa: string;
    graduationYear: string;
    application: IntakeApplication;
    resultType: ResultType;
    institution: string;
    program: string;
}