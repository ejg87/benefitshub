import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../core/services/token-storage.service';
import { BenefitService } from '../../core/services/benefit.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  user: any = null;
  benefits: any[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(
    private tokenStorage: TokenStorageService,
    private benefitService: BenefitService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
    this.loadBenefits();
  }

  loadBenefits(): void {
    this.benefitService.getMyBenefits().subscribe(
      (data) => {
        this.benefits = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      (err) => {
        this.errorMessage = 'Failed to load benefits';
        this.isLoading = false;
        this.cdr.detectChanges();
      }
    );
  }

  logout(): void {
    this.tokenStorage.signOut();
    this.router.navigate(['/login']);
  }

  getBenefitTypeClass(type: string): string {
    const classes: any = {
      'HEALTH': 'badge-health',
      'DENTAL': 'badge-dental',
      'VISION': 'badge-vision',
      'PTO': 'badge-pto',
      'RETIREMENT': 'badge-retirement'
    };
    return classes[type] || 'badge-default';
  }
}