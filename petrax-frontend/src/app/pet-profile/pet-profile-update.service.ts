import { Injectable } from '@angular/core';
// This decorator is used to tell Angular that this class can be
// injected with dependencies, making it an injectable service.
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PetProfileUpdateService {
  private petDeletedSubject = new Subject<number>();
//A Subject is a special type of observable that can both act as an observer and
// emit data to its subscribers. In this case, it's used to notify subscribers
// when a pet profile has been deleted.
  petDeleted$ = this.petDeletedSubject.asObservable();

  triggerPetDeleted(petId: number) {
    this.petDeletedSubject.next(petId);
  }
}
