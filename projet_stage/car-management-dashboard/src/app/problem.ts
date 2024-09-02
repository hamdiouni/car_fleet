// src/app/services/problem.service.ts

export interface Problem {
    id: number;
    employeeId: number;
    description: ProblemStatus;
    timestamp: Date;
    status: ProblemStatus;
}

export enum ProblemStatus {
    REPORTED = 'REPORTED',
    IN_PROGRESS = 'IN_PROGRESS',
    REACTED = 'REACTED',
    RESOLVED = 'RESOLVED'
}
