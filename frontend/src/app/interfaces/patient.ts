export interface Patient {
    id: string,
    firstName: string,
    lastName: string,
    height: number,
    weight: number,
    ssn: string,
    diagnosed: boolean,
    currentlyInHospital: boolean,
    stationId?: string
    dob: string
}
