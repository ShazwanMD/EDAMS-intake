import { StudyMode } from './../../../shared/model/common/study-mode.interface';
import {
  Component,
  Input,
  EventEmitter,
  Output,
  ChangeDetectionStrategy,
  OnInit,
  AfterViewInit,
  ViewContainerRef,
} from '@angular/core';
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {StudyModeEditorDialog} from './dialog/study-mode-editor.dialog';
@Component({
  selector: 'pams-study-list.page',
  templateUrl: './study-mode-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class StudyModeListPage implements OnInit{
  private STUDY_MODES = "setupModuleState.studyModes".split(".");
  private studyModes$: Observable<StudyMode[]>;
  private creatorDialogRef: MdDialogRef<StudyModeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.studyModes$ = this.store.select(...this.STUDY_MODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findStudyModes());
    this.store.dispatch(this.actions.changeTitle("Kod Mod Pengajian"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:StudyMode): void {
    this.showDialog(code);
  }
  delete(code: StudyMode): void {
    this.store.dispatch(this.actions.removeStudyMode(code))
  }

  private showDialog(code:StudyMode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(StudyModeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.studyMode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
