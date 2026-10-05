# Testing Plan & Verification Details

### Test 1: Successful login
- **Action**: Enter `john@student.campus.edu` on the login page.
- **Result**: Redirects to the availability listings, sets server-session parameters, and shows "Welcome, John Doe" in the top right.

### Test 2: Invalid login
- **Action**: Enter `fake@fake.com`
- **Result**: Page reloads gracefully. A Bootstrap alert box displays the red "Email not found in database." prompt.

### Test 3: Display available slots
- **Action**: Go to Availability.
- **Result**: Iterates over SQL rows via DAOs and JSTL. If a ride has 0 remaining capacity, the button renders greyed out saying "FULL" and sets `disabled`.

### Test 4: Successful booking
- **Action**: Pick "EV Shuttle", click "Book Now", then "Confirm Reserve".
- **Result**: Processes the transaction. The success view displays the exact CO2 metric saved over a private transport. Slot increments count in DB. Student total cumulative CO2 is synced.

### Test 5: Booking a full slot (Concurrency Safety)
- **Mechanism Check**: The database requires `booked_count < capacity` for any decrement. Trying to maliciously hit the `/booking` POST endpoint on a full ID fails atomic matching and returns the clean error: "The vehicle slot is full."

### Test 6: Duplicate booking
- **Mechanism Check**: A duplicate checking guard `SELECT COUNT(*) FROM Bookings WHERE student_id = ? AND slot_id = ?` ensures rolling back if previously booked, preventing dirty identical trips. A dedicated error response is shown.

### Test 7: CO2 calculation Formula
- **Mechanism**: The `CarbonCalculator` runs `distance_km * (0.192 - 0.040)` against constant bounds, converting it with `RoundingMode.HALF_UP` inside `BigDecimal` for safe double serialization representing Kg.

### Test 8: Session Security Protection
- **Action**: Click Logout. Observe `session.invalidate()` runs. Enter URL `/availability` directly into browser.
- **Result**: Filter triggers `response.sendRedirect(..)` back to `/login`.

### Test 9: Cookie Preferences Memory
- **Action**: Finish a successful booking of an "EV Shuttle". Close tab. Log in later and hit availability.
- **Result**: A banner is dynamically appended identifying "Your last selected vehicle type was: EV Shuttle" extracted safely from local cookie variables.
