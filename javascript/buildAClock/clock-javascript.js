export class Clock {
    constructor(hours=0, minutes=0) {
        this.hours = hours;
        this.minutes = minutes;
        this.recalculate();
      }
      recalculate() {
        this.hours += Math.floor(this.minutes / 60);
        this.minutes = (this.minutes % 60 + 60) % 60;
        this.hours = (this.hours % 24 + 24) % 24;
      }


      toString() {
        const paddedHours = String(this.hours).padStart(2, '0');
        const paddedMinutes = String(this.minutes).padStart(2, '0');
        return `${paddedHours}:${paddedMinutes}`;
      }

      plus(minutes) {
        this.minutes += minutes;
        this.recalculate();
        return this;
      }

      minus(minutes) {
        return this.plus(-minutes);
      }

      equals(otherClock) {
        return this.hours === otherClock.hours && this.minutes === otherClock.minutes;
      }
  }
