import { Employment } from './../employment.interface';
import { Intake } from './../../../policy/intakes/intake.interface';
import { Applicant } from './../../../identity/applicant.interface';
import { ProgramOffering } from './../../../policy/intakes/program-offering.interface';
import { ReligionCode } from './../../../common/religion-codes/religion-code.interface';
import { GenderCode } from './../../../common/gender-codes/gender-code.interface';
import { EthnicityCode } from './../../../common/ethnicity-codes/ethnicity-code.interface';
import {ProgramLevel} from "../../../policy/program-levels/program-level.interface";
import { StudyMode } from "../../../common/study-modes/study-mode.interface";
import { DisabilityCode } from "../../../common/disability-codes/disability-code.interface";
import { RaceCode } from "../../../common/race-codes/race-code.interface";

export interface IntakeApplication {

  startDate: Date;
  endDate: Date;
  employer:String;
  id: number;
  rank: number;
  merit: number;
  referenceNo: String;
  name: String;
  email: String;
  phone: String;
  fax: String;
  credentialNo: String;
  okuNo: String;
  paymentSourceNo: String;
  age: number;
  //schoolCode: SchoolCode;
  schoolName: String;
  schoolBatch: number;
  reason: String;
  passport: String;
  passExpDate: Date;
  visaType: number;
  bidType: number;
  bidStatus: number;
  bidResponse: number;
  studyMode: StudyMode;
  disabilityCode: DisabilityCode;
  raceCode: RaceCode;
  ethnicityCode: EthnicityCode;
  genderCode: GenderCode;
  religionCode: ReligionCode;
  programSelection: ProgramOffering;
  intake: Intake;
  applicant: Applicant;
  //results: Results;
  //educations: Educations;
  employments: Employment;
  //addresses: Addresses;
  //referees: Referees;
  //attachments: Attachments;
  // transient
  paid?:boolean;
  verification?:boolean;
}
