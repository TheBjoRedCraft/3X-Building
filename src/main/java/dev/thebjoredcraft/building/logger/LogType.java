package dev.thebjoredcraft.building.logger;

/*
 * Copyright © 2024 TheBjoRedCraft. All rights reserved.
 *
 * This file contains proprietary information belonging to TheBjoRedCraft.
 * No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior
 * written permission of TheBjoRedCraft, except in the case of brief quotations embodied in critical
 * reviews and certain other noncommercial uses permitted by copyright law.
 *
 * Unauthorized use, reproduction, or distribution of this code or any portion of it may result in severe
 * penalties, and will be prosecuted to the maximum extent possible under the law.
 */


public enum LogType {
    WARN("[WARN]"),
    ERROR("[ERROR]"),
    INFO("[INFO]"),
    MESSAGE(""),
    OTHER("");

    public final String prefix;
    LogType(String prefix){
     this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
