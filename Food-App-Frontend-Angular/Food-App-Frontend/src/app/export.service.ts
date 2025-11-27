import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExportService {

  exportToCSV(filename: string, rows: any[]) {
    if (!rows || !rows.length) return;

    const separator = ",";
    const keys = Object.keys(rows[0]);

    let csvContent = keys.join(separator) + "\n";

    rows.forEach(row => {
      csvContent += keys.map(k => `"${row[k] ?? ""}"`).join(separator) + "\n";
    });

    const blob = new Blob([csvContent], { type: "text/csv;charset=utf-8;" });
    const url = window.URL.createObjectURL(blob);

    const a = document.createElement("a");
    a.href = url;
    a.download = `${filename}.csv`;
    a.click();

    window.URL.revokeObjectURL(url);
  }
}
