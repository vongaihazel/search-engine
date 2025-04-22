
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideHttpClient, withFetch } from '@angular/common/http';

const config = {
  ...appConfig,
  providers: [
    ...appConfig.providers,
    provideHttpClient(withFetch()) // Enable fetch
  ]
};

bootstrapApplication(AppComponent, config)
  .catch((err) => console.error(err));
