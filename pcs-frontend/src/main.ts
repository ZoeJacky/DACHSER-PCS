import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { App } from './app/app';

bootstrapApplication(App, appConfig)
  .catch((err) => console.error(err));

// bootstrapApplication(App, {
//   providers: [
//     importProvidersFrom(HttpClientModule), // Add HttpClientModule to providers
//     importProvidersFrom(RouterModule.forRoot(routes)) // If you're using routing
//   ]
// }).catch(err => console.error(err));
