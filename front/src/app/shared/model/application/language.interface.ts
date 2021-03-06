import {MetaObject} from '../../../core/meta-object.interface';
import {LanguageCode} from '../common/language-code.interface';
export interface Language extends MetaObject {
  languageCode: LanguageCode;
  oral: number;
  written: number;

  selected?: boolean;
}
