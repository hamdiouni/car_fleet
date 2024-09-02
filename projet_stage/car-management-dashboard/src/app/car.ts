export interface Car {
    id: number;
    model: string;
    licensePlate: string;
    status: string;
    technicalVisitDate: string;
    leasingContract?: string;
    insurance?: string;
    technicalVisit?: string;
    employeeIdentityCard: string;  // Added field

}
