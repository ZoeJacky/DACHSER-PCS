import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Import HttpClient for HTTP requests
import { Observable } from 'rxjs'; // Observable is used for asynchronous operations

@Injectable({
  providedIn: 'root', // Makes the service available globally throughout the app
})
export class IncomeService {
  private apiUrl = 'http://localhost:8080/api/incomes'; // URL of the backend API (Replace with actual API endpoint)

  // Injecting HttpClient to make HTTP requests
  constructor(private http: HttpClient) {}

  /**
   * Method to get all incomes.
   * Returns an Observable that resolves to the list of incomes.
   */
  getIncomes(): Observable<any> {
    return this.http.get<any>(this.apiUrl); // HTTP GET request to fetch all incomes
  }

  /**
   * Method to delete an income by its ID.
   * Returns an Observable that resolves when the income is deleted.
   */
  deleteIncome(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`); // HTTP DELETE request to remove an income
  }

  /**
   * Method to add a new income.
   * Accepts an income object and returns an Observable that resolves to the added income.
   */
  addIncome(income: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, income); // HTTP POST request to add a new income
  }

  /**
   * Method to update an existing income by its ID.
   * Accepts an ID and an income object with updated data, 
   * and returns an Observable that resolves to the updated income.
   */
  updateIncome(id: number, income: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, income); // HTTP PUT request to update income data
  }

  /**
   * Method to get a single income by its ID.
   * Returns an Observable that resolves to the income object.
   */
  getIncome(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`); // HTTP GET request to fetch a specific income
  }
}
