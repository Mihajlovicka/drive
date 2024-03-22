import { inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  createUrlTreeFromSnapshot,
} from '@angular/router';
import { map } from 'rxjs';
import {UserService} from "../service/user.service";

export const authGuard = (next: ActivatedRouteSnapshot) => {
  return inject(UserService)
    .isLoggedInObs()
    .pipe(
      map((isLoggedIn) =>
        isLoggedIn ? true : createUrlTreeFromSnapshot(next, ['/', 'login'])
      )
    );
};
